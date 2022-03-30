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

## CI/CD with Auto DevOps
This project is configured with pipelines to ensure code quality using unit tests, build checks and auto-deployment using the Heroku cloud server.

## Development Environment 
base URL - https://app-staff-scheduler-dev.herokuapp.com  
swagger UI - https://app-staff-scheduler-dev.herokuapp.com/swagger-ui/index.html  
swagger api-docs - https://app-staff-scheduler-dev.herokuapp.com/api-docs

## Testing Environment
base URL - https://app-staff-scheduler-test.herokuapp.com  
swagger UI - https://app-staff-scheduler-test.herokuapp.com/swagger-ui/index.html  
swagger api-docs - https://app-staff-scheduler-test.herokuapp.com/api-docs

## Production Environment
base URL - https://app-staff-scheduler.herokuapp.com  
swagger UI - https://app-staff-scheduler.herokuapp.com/swagger-ui/index.html  
swagger api-docs - https://app-staff-scheduler.herokuapp.com/api-docs/

### Environment selectors
spring.profiles.active=develop  
spring.profiles.active=test  
spring.profiles.active=prod  
