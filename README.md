## *Implementing Hibernate with Spring Boot and PostgreSQL*

**Installations and set up**

You can download a project with a ready-to-use pom.xml file from [here](https://start.spring.io).

Add the following dependencies and click GENERATE
- Spring Web
- PostgreSQL Driver
- Spring Data JPA

You should also [download](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and install postgreSQL.

Firstly, here is everything we need to have in *application.properties* file.

Postgres properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=___
```
Hibernate properties:
```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto = update
```
And by doing this Spring Boot will allow bean overriding without any change to bean definitions.
```
spring.main.allow-bean-definition-overriding=true
```


