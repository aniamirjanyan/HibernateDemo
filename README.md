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

Aside from the simple GET and POST methods here is an example of a query using two parameters. You can read about various query methods [here](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods).

Declare the method in *BookRepository.java* class like this:
```
List<Book> findByTitleAndAuthor(String title, String author);
```
And continue in *Controller.java* class like this:
```
@GetMapping("/findbytitleandauthor/")
public ResponseEntity<List<Book>> findByTitleAndAuthor(@RequestParam String title, 
                                                       @RequestParam String author) {
    List<Book> books = bookRepository.findByTitleAndAuthor(title, author);
    return new ResponseEntity<>(books, HttpStatus.OK);
}
```


