

# My Spring Boot Application

A simple RESTful API for managing a library's book collection. The application is built with Spring Boot, Spring Data JPA, and Hibernate, using a PostgreSQL database for persistence.

## 📁 Directory Structure

The project follows a standard Maven and Spring Boot directory layout, separating code, resources, and build artifacts into their respective directories.

```

.
├── HELP.md                      \# Default help file from Spring Initializr
├── mvnw                         \# Maven Wrapper script for platform-independent builds
├── pom.xml                      \# Project Object Model file, defining dependencies and build configuration
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── my\_spring\_boot\_app
│   │   │               ├── controller
│   │   │               │   └── BookController.java      \# Handles all API endpoints
│   │   │               ├── model
│   │   │               │   └── Book.java                \# JPA Entity representing the 'book' table
│   │   │               ├── MySpringBootAppApplication.java \# Main Spring Boot application class
│   │   │               ├── repository
│   │   │               │   └── BookRepository.java      \# Data access layer for the Book entity
│   │   │               └── service
│   │   │                   └── BookService.java         \# Business logic layer
│   │   └── resources
│   │       ├── application.properties   \# Main configuration file (database, server port, etc.)
│   │       ├── data.sql                 \# SQL script for initial data population
│   │       ├── static                   \# Directory for static web content (not used in this API)
│   │       └── templates                \# Directory for server-side templates (not used in this API)
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── my\_spring\_boot\_app
│                       └── MySpringBootAppApplicationTests.java \# Sample test file
└── target                       \# Maven's build output directory (generated on build)

````

## 🚀 Getting Started

These instructions will get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java Development Kit (JDK) 21 or later
* Apache Maven
* PostgreSQL database
* An IDE (e.g., Visual Studio Code, IntelliJ IDEA)

### 1. Database Setup

First, ensure you have a running PostgreSQL instance. Create a new database for the application.

```sql
-- Connect to your PostgreSQL server and run this command
CREATE DATABASE my_app_db;
````

### 2\. Configuration

Open the `src/main/resources/application.properties` file and update the database connection details to match your setup.

```properties
# Spring Application Properties
server.port=8081
server.address=0.0.0.0

spring.application.name=my-spring-boot-app

# PostgreSQL Datasource Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/my_app_db
spring.datasource.username=postgres
spring.datasource.password=postgres

# Hibernate and JPA Properties
# 'update' is a good setting for development, as it will persist data on restart.
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Tells Spring to run data.sql only once. Change to 'always' if you want it to run on every startup.
spring.sql.init.mode=once

# H2 configuration is commented out as it is no longer used.
# spring.h2.console.enabled=true
# spring.datasource.url=jdbc:h2:mem:mydatabase
# spring.datasource.driverClassName=org.h2.Driver
# spring.datasource.username=sa
# spring.datasource.password=password
# spring.jpa.hibernate.ddl-auto=update
```

### 3\. Running the Application

There are two primary ways to run the application:

**From the terminal:**

```bash
./mvnw spring-boot:run
```

**From your IDE:**
Locate the `MySpringBootAppApplication.java` file and run it directly.

## 🌐 API Endpoints

The API is accessible on `http://localhost:8081`. The following endpoints are available:

### Books API

#### **GET /api/books**

  - **Description:** Retrieves a list of all books.
  - **Example Response:**
    ```json
    [
      {
        "id": 1,
        "title": "The Hitchhiker's Guide to the Galaxy",
        "author": "Douglas Adams"
      }
    ]
    ```

#### **GET /api/books/{id}**

  - **Description:** Retrieves a single book by its ID.
  - **Example URL:** `http://localhost:8081/api/books/1`
  - **Example Response:**
    ```json
    {
      "id": 1,
      "title": "The Hitchhiker's Guide to the Galaxy",
        "author": "Douglas Adams"
    }
    ```

#### **POST /api/books**

  - **Description:** Creates a new book record.
  - **Example Request Body:**
    ```json
    {
      "title": "The Lord of the Rings",
      "author": "J.R.R. Tolkien"
    }
    ```
  - **Example Response:** (Returns the saved object with its new ID)
    ```json
    {
      "id": 2,
      "title": "The Lord of the Rings",
      "author": "J.R.R. Tolkien"
    }
    ```

## 📚 Tools and Technologies

  * **Spring Boot**: The foundation of the application.
  * **Spring Data JPA**: Simplifies data access with repositories.
  * **Hibernate**: The JPA provider for object-relational mapping.
  * **PostgreSQL**: The relational database for data persistence.
  * **SpringDoc OpenAPI**: Automatically generates interactive API documentation.

## 📝 API Documentation (Swagger UI)

For a complete and interactive overview of the API, you can access the Swagger UI documentation at:

**`http://localhost:8081/swagger-ui.html`**

This is an excellent resource for testing endpoints directly from your browser.
