# How to connect to the database.
This app runs with a Postgres database.

Once the database is up and running, go to `src/main/resources/application.properties`
and update first three properties:
```
spring.datasource.url=jdbc:postgresql://host:port/database_name
spring.datasource.username=username
spring.datasource.password=password
```
