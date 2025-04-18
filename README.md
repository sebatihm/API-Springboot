# Practica 2 Crear API con Springboot y dockerizarla
Se nos encargo construir un API con Spring Boot, llevando seguimiento de cambios en git y GitHub,  para finalmente
construir una imagen de Docker de la aplicación y publícarla en el repositorio de DockerHub.

El proyecto a desarrollar tomo de guia el libro Practical-Guide-to-building-an-API-backend-with-Spring-Boot

![image](https://github.com/user-attachments/assets/399f4474-d704-4119-98aa-f0e8b558f67f)


## Ejecución del programa
La rama master tiene el desarrollo del API REST desde el inicio del libro, el proyecto en si esta hecho usando maven con sus propias dependencias, por lo cual se debería tener instalado maven. Sin embargo se puede usar el maven-wrapper (mvnw) por ejemplo para ejecutar el proyecto se usa: 

* mvn spring-boot:run 

Usando el maven-wrapper seria: 

* ./mvnw spring-boot:run

En fin, para poder ejecutar la aplicación se necesita tener docker instalado pues se estarán usando contenedores para su prueba. Primero se deberá ejecutar el siguiente comando:

* docker-compose up -d

El cual corre el docker-compose.yaml del programa el cual ejecuta en segundo plano la base de datos en postgre y la instancia de keycloack, esta ultima utiliza el archivo "realm-export.json" la cual contiene la información para ejecutar keycloack con el servicio necesario para la ejecución del programa.

Una vez que los contenedores están funcionando se ejecuta el proyecto mediante el comando:

* ./mvnw spring-boot:run

## Comandos utilizados

* docker-compose up -d
* ./mvnw spring-boot:run
