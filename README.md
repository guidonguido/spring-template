# Quick Spring MVC Warehouse Service implementation

This project is intended to be considered as a template for the implementation of a REST server using Spring.

## Hybernate configuration

In order to configure the connection with MariaDB, create the file `/src/main/resources/application.properties` ancd configure the following props:

```arduino
spring.datasource.url=jdbc:mariadb://localhost:3306/DB_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.show-sql=false
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```

## Exposed Endpoints

- **/warehouse/products [POST]**: Add a product, with a given initial quantity, to the warehouse
- **/warehouse/products/{productID} [PATCH]**: Update the quantity of the product available in the
warehouse. Use a negative number to take a given quantity of the product out from the Warehouse.
If the resulting quantity is negative, the operation will fail, leaving the product unmodified, otherwise the updated product is returned.
- **/warehouse/products/{productID} [GET]**: Get the product by id
- **/warehouse/products [GET]**: Get all products
- **/warehouse/productsByCategory?category=<category> [GET]:** Get all products by a given category