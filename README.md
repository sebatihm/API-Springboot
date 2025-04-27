# Docker
En este rama se encuentran todas las configuraciones que fueron utlizadas para dockerizar el proyecto la rama contiene un Dockerfile que se utiliza para generar la imagen y un archivo llamado docker-prod.yaml el cual deberia de ejecutar las 3 imagenes en un entorno dockerizado.

## DockerFile
En el dockerfile se uso la imagen openjdk21-slim para su ejecucion ya que el proyecto esta hecho en java, una vez hecho esto se copian todos los archivos del fichero donde esta el proyecto a la carpeta `/usr/src/myapp"`
una vez hecho esto se establece esta misma carpeta como directorio de trabajo y finalmente con ayuda del comando CMD se establece que al momento de crear un contenedor se usara el comando ``./mvnw spring-boot:run``
el cual sirve para ejecutar el proyecto incluso en un entorno donde no se tiene instalado maven (el cual es el caso), utilizando el maven-wrapper.

```
FROM openjdk:21-slim
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ./mvnw spring-boot:run
```

## docker-prod
Este archivo se encarga de configurar el entorno de las imagenes para la dockerizacion de estas mismas, es decir configura las imagenes creadas de tal manera que funcionen sin problemas en un entorno dockerizado.

```
version: '1'

services:
  db:
    image: postgres:16.0
    container_name: db
    ports:
      - "5432:5432"
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: my-postgres-db-pwd
      POSTGRES_DB: copsbootdb
    volumes:
      - postgres-data:/var/lib/postgresql/data

  identity:
    image: quay.io/keycloak/keycloak:22.0.1
    container_name: keycloak
    command: "start-dev --import-realm"
    ports:
      - "8180:8080"
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin-secret
      KC_HEALTH_ENABLED: "true"
      KC_METRICS_ENABLED: "true"
    volumes:
      - ./realm-export.json:/opt/keycloak/data/import/realm-export.json:ro
    depends_on:
      - db


  app:
    image: sebatihm/api-springboot:0.3
    container_name: springboot-app
    ports:
      - "8080:8080"
    depends_on:
      - db
      - identity

volumes:
  postgres-data:

```

Sin embargo a pesar de que se logra ejecutar, como la guia marca que se tienen que obtener los token desde la maquina local (no los contenedores) y esto causa problemas con el cliente de keycloack ya que los issuer no coinciden lo cual hace que el token sea invalido.
Aun asi el proyecto se puede ejecutar sin problemas en un entorno local

## Ejecucion
Para correr el docker-prod.yaml solo se necesita correr este comando.
``docker-compose -f docker-prod.yaml up``
