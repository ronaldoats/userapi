# UserApi
## Ronaldo Antonio Tinoco Salgado

Proyecto Java - framework de Spring Boot.
Generacion de Token JWT, creacion de usuario, consulta de usuarios.

## Caracteristicas del Mini Proyecto
- Generacion de Token JWT
- Creacion de Usuario
- Consulta de Usuarios
- Validaciones Incluidas en el modelo

## Tecnologias Usadas

Dillinger uses a number of open source projects to work properly:

- [Java 8+] - Java
- [Spring Boot Starter Web]
- [Spring Boot]
- [Spring JPA]
- [H2] - Base de datos
- [JWT]
- [Spring Validation]
- [Lombok]

## Requerimientos

[Git](https://git-scm.com/downloads)
[Maven](https://maven.apache.org/install.html)
[Java]

Modo de uso

```sh
git clone https://github.com/ronaldoats/userapi.git
cd userapi
mvn clean install -U 
mvn spring-boot:run
```

Consumo de la aplicacion usando curl...

Asumiendo que la aplicacion esta corriendo en el puerto 8080, por defecto se inserta un usuario ronaldots@live.com

-----
###### Curl - Obtener Token
##
```sh
curl --location --request POST 'http://localhost:8080/api/auth' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=5EE1B40F6C0B7165E77B41BE7676112B' \
--data-raw '{
    "email": "ronaldots@live.com",
    "password": "password$$12.."
}'
```
###### Body Response - Token ejemplo
##

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb25hbGRvdHNAbGl2ZS5jb20iLCJyb2xlcyI6W10sImlhdCI6MTY2NzI2NzUzNSwiZXhwIjoxNjY3NDQwMzM1fQ.da3fHGEykKNiqwgdGGGNR3q3LTIdUQicvgi4DeZfnSA"
}
```
#
-----
#
#
#
#
-----
#
###### Curl - Registrar usuario con el Token Obtenido
##
```sh 
curl --location --request POST 'http://localhost:8080/api/users' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb25hbGRvdHNAbGl2ZS5jb20iLCJyb2xlcyI6W10sImlhdCI6MTY2NzI2NTQyNiwiZXhwIjoxNjY3NDM4MjI2fQ.iThB7MwDuku_co81dz3Hp762EFP-nuiOMCg0qruOIh4' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"usuario2", 
    "email":"mail2@mail.com",
    "password":"Abc$$505..", 
    "phones":[
        {
            "number":"01923213", 
            "cityCode":1,
            "countryCode":"2123"
        }
    ]
}'
```
###### Body Response - Usuario Creado
##

```json
{
    "id": "c0a80109-8430-19d6-8184-30e680f30002",
    "created": "2022-10-31T19:55:23.119137",
    "modified": "2022-10-31T19:55:23.119185",
    "lastLogin": null,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWlsMkBtYWlsLmNvbSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJhZG1pbiJ9XSwiaWF0IjoxNjY3MjY3NzIzLCJleHAiOjE2Njc0NDA1MjN9.TUrVLqfPVE0OMJzTb3U1WjTQbqhTaVaTTz3tuqbPYw0",
    "active": true
}
```
#
-----
#
#
#
#
-----
#

###### Curl - Consultas todos los usuarios
##

```sh
curl --location --request GET 'http://localhost:8080/api/users' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb25hbGRvdHNAbGl2ZS5jb20iLCJyb2xlcyI6W10sImlhdCI6MTY2NzI2NTQyNiwiZXhwIjoxNjY3NDM4MjI2fQ.iThB7MwDuku_co81dz3Hp762EFP-nuiOMCg0qruOIh4' \
--header 'Cookie: JSESSIONID=5EE1B40F6C0B7165E77B41BE7676112B'
```
###### Body Response - Lista de usuarios
##


```json
{
    "mensaje": "Lista de usuarios",
    "body": [
        {
            "id": null,
            "name": "Ronaldo Tinoco",
            "password": "$2a$12$vZGdE69nkBSpYyUf9eKkU.hLA4m.Zpm2HwFVDzBCu3RaZf2e9c8fS",
            "email": "ronaldots@live.com",
            "phones": []
        },
        {
            "id": null,
            "name": "ronaldo",
            "password": "$2a$12$YNTHnviDY3cICezOuWSvQeHTO1eb7n8vSZuvYRnW1w2BtUkQcnWdS",
            "email": "sajdkfjasdfs@f.com",
            "phones": []
        },
        {
            "id": null,
            "name": "usuario2",
            "password": "$2a$12$5gkqF3fciCq.A1f5ctz8LOPXKvjpvokGLzFAoNMHIPbaos7.9H8vi",
            "email": "mail2@mail.com",
            "phones": []
        }
    ]
}
```


## 
