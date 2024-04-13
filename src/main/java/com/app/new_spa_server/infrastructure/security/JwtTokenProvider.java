package com.app.new_spa_server.infrastructure.security;

import com.app.new_spa_server.domain.configuration.AppProperties;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Jwts.SIG.HS512;

@Component
@Slf4j
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "roles";
    private final AppProperties appProperties;
    private SecretKey secretKey;

    public JwtTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @PostConstruct
    public void init() {
        var secret = Decoders.BASE64.decode(this.appProperties.getJwtSecret());
        this.secretKey = Keys.hmacShaKeyFor(secret);
    }

    public String createToken(Authentication authentication) {
        var username = authentication.getName();
        var authorities = authentication.getAuthorities();
        var claims = Jwts.claims().subject(username);
        if (!authorities.isEmpty()) {
            claims.add(AUTHORITIES_KEY, authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","))
            );
        }

        var now = new Date();
        var validity = new Date(now.getTime() + this.appProperties.getJwtExpiration().toMillis());

        return Jwts.builder()
                .claims(claims.build())
                .issuedAt(now)
                .expiration(validity)
                .signWith(this.secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        var claims = Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        var authoritiesClaim = claims.get(AUTHORITIES_KEY);
        var authorities = authoritiesClaim == null
                ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils
                .commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
        var principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(this.secretKey)
                    .build()
                    .parseSignedClaims(token);
            log.info("expiration date: {}", claims.getPayload().getExpiration());
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

}