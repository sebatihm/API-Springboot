FROM openjdk:21-slim
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ./mvnw spring-boot:run