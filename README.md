## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.scheduler.app.StaffSchedulerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### CI/CD with Auto DevOps

This project is configured with pipelines to ensure code quality using unit tests, build checks and auto-deployment using the Heroku cloud server.

## Production applicaiton URL
https://app-staff-scheduler.herokuapp.com/

## Environment selectors
spring.profiles.active=develop
spring.profiles.active=test
spring.profiles.active=prod
