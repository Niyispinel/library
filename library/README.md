# springboot-sample-app

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) library app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](http://maven.apache.org/POM/4.0.0)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `library/src/main/java/com/library/api/LibraryApiApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Swagger Configuration has been implemented and can be accessed on http://localhost:8084/library/swagger-ui.html

Swagger postman collection can be retrieved on http://localhost:8084/library/v2/api-docs

The project runs on port 8084

The Database use is mysql and the configuration is in the application.properties file