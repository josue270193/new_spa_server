# New Spa
Aplicativo para tener un listado de tareas, segurizado para que cada usuario pueda ver y 
administrar sus propias tareas. 

- Java 17
- Spring Boot 2.7
- H2
- Spring Security con JWT
- Open API 3.1

<!-- TOC -->
# Contenido
* [New Spa](#new-spa)
    * [Compilado](#compilado)
    * [Ejecucion](#ejecucion)
    * [Prueba](#prueba)
<!-- TOC -->

## Compilado

El proyecto utiliza Maven con Java 17, para el compilado. Por ende se debe tomar en cuenta que se debe generar las clases,
que corresponden al [OpenApi (Ir al archivo)](src%2Fmain%2Fresources%2Fopenapi3_1.yaml).

Para generar las clases de la OpenApi se debe ejecutar el siguiente comando:

`mvn clean compile`

## Ejecucion

Como el aplicativo usa una base de datos H2, se configuro para que se pueda utilizar por medio de un archivo que se generara,
al momento de ejecucion. Ademas que se utiliza Spring boot, es por ello que se puede ejecutar el aplicacion.

Para ejecutar, se debe utilizar el siguiente comando:

`mvn spring-boot:run`

## Prueba

Para las pruebas, se recomienda la utilizacion del Postman y se debera exportar la [collection (Ir al archivo)](postman%2Fnew_spa_server.postman_collection.json)
que se encuentra dentro del proyecto.

Ya dentro del postman y exportado correctamente la collection.

Se encontrar 2 carpetas para agrupar los endpoint que son del usuario y para las tareas(tasks)

![Bildschirmfoto 2024-04-13 um 03.29.39.png](docs%2FBildschirmfoto%202024-04-13%20um%2003.29.39.png)

1. Los endpoint que tienen usuarios son publicos y no tienen ninguna seguridad.
2. Los endpoint que tienen las tareas si requieren un token (JWT) por ende no son publicos y debe proceder a logearse.
3. Dentro del postman, ya se encuentra configurado que al ejecutarse el endpoint de Login, proceda a logearse con un usuario creado posterior a la ejecutcion del proyecto.
4. En caso que necesita ejecutarlo a mano, debera ejecutar el siguiente curl.
```
curl --location 'localhost:8080/api/public/v1/user/login' \
--header 'Content-Type: application/json' \
--data '{
"username": "test",
"password": "test"
}'
```
5. Ya con el token seteado dentro del postman, puede proceder a ejecutar los endpoint correspondiente a las tareas.
![Bildschirmfoto 2024-04-13 um 03.30.32.png](docs%2FBildschirmfoto%202024-04-13%20um%2003.30.32.png)
6. Por ejemplo, la creacion de una tarea.
![Bildschirmfoto 2024-04-13 um 03.31.03.png](docs%2FBildschirmfoto%202024-04-13%20um%2003.31.03.png)
