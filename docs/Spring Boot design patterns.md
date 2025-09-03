# 📘 Spring & Spring Boot Design Patterns – Detailed Documentation

## 1. Introduction

Spring and Spring Boot are **enterprise Java frameworks** that simplify application development using **inversion of control (IoC)**, **dependency injection (DI)**, and **aspect-oriented programming (AOP)**.

Behind the scenes, Spring leverages many **design patterns** from the *Gang of Four (GoF)* and enterprise architecture patterns. Understanding these patterns helps developers build **modular, maintainable, and testable applications**.

---

## 2. Categories of Design Patterns in Spring

Spring integrates **three major categories of patterns**:

1. **Creational Patterns** – Object creation & bean lifecycle
2. **Structural Patterns** – Composition of beans, proxies, and decorators
3. **Behavioral Patterns** – Object collaboration & communication

---

## 3. Creational Patterns

### 3.1 Dependency Injection (DI) / Inversion of Control (IoC)

* **Definition**: Objects don’t create their dependencies; the container injects them.
* **Spring Usage**: `@Autowired`, `@Qualifier`, `@Value`, constructor injection.

```java
@Service
class OrderService {
    private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

✔ Promotes **loose coupling**, **testability**, and **modularity**.

---

### 3.2 Singleton

* **Definition**: Ensures only one instance of a class exists in the container.
* **Spring Usage**: By default, all beans in Spring are **singletons** (per ApplicationContext).

```java
@Component
public class CacheManager {
   // Only one instance across the container
}
```

⚠️ Unlike the GoF singleton, Spring’s singleton is **scoped to the container**, not the JVM.

---

### 3.3 Prototype

* **Definition**: Creates a **new bean instance** every time it is requested.
* **Spring Usage**: `@Scope("prototype")`

```java
@Component
@Scope("prototype")
public class UserSession {}
```

---

### 3.4 Factory Method

* **Definition**: Provides an interface for creating objects without exposing instantiation logic.
* **Spring Usage**: `BeanFactory`, `@Bean` methods in `@Configuration` classes.

```java
@Configuration
class AppConfig {
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
```

---

### 3.5 Builder (Fluent APIs)

* **Definition**: Step-by-step construction of complex objects.
* **Spring Usage**:

  * `RestTemplateBuilder`
  * `MockMvcBuilders`
  * `UriComponentsBuilder`

```java
RestTemplate restTemplate = new RestTemplateBuilder()
                                .setConnectTimeout(Duration.ofSeconds(2))
                                .build();
```

---

## 4. Structural Patterns

### 4.1 Proxy

* **Definition**: Provides a surrogate object to control access to another object.
* **Spring Usage**:

  * AOP Proxies (method interception for logging, transactions, security).
  * `@Transactional` creates proxies for transaction management.

```java
@Service
@Transactional
class OrderService {
   public void placeOrder() { /* wrapped in proxy transaction */ }
}
```

---

### 4.2 Decorator

* **Definition**: Dynamically adds responsibilities to objects.
* **Spring Usage**: `DelegatingFilterProxy` in Spring Security filters.

---

### 4.3 Adapter

* **Definition**: Converts one interface into another.
* **Spring Usage**:

  * `HandlerAdapter` maps requests to controller methods.
  * `WebMvcConfigurer` adapts configuration for MVC.

---

### 4.4 Facade

* **Definition**: Provides a unified interface to a set of subsystems.
* **Spring Usage**:

  * `JdbcTemplate`, `RestTemplate`, `KafkaTemplate` → hide complexity of low-level APIs.

---

## 5. Behavioral Patterns

### 5.1 Template Method

* **Definition**: Defines a skeleton of an algorithm, subclasses override certain steps.
* **Spring Usage**:

  * `JdbcTemplate`, `RestTemplate`, `TransactionTemplate`

```java
jdbcTemplate.query("SELECT * FROM users",
   (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name")));
```

✔ Removes **boilerplate code** (connection handling, exceptions).

---

### 5.2 Observer

* **Definition**: Defines a one-to-many dependency between objects.
* **Spring Usage**: Event-driven programming with `ApplicationEventPublisher`.

```java
@Component
class UserEventListener {
    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        System.out.println("User created: " + event.getUsername());
    }
}
```

---

### 5.3 Strategy

* **Definition**: Encapsulates algorithms and makes them interchangeable.
* **Spring Usage**:

  * `PlatformTransactionManager` implementations (JPA, JDBC, Hibernate).
  * Spring Security’s authentication strategies.

---

### 5.4 Command

* **Definition**: Encapsulates requests as objects.
* **Spring Usage**:

  * `CommandLineRunner` and `ApplicationRunner` in Spring Boot.

```java
@Component
class StartupTask implements CommandLineRunner {
    public void run(String... args) {
        System.out.println("App started with args: " + Arrays.toString(args));
    }
}
```

---

### 5.5 Chain of Responsibility

* **Definition**: Passes requests along a chain until handled.
* **Spring Usage**:

  * Servlet filters (`FilterChain`)
  * Spring Security filter chains.

---

## 6. Benefits of Using Patterns in Spring

* **Reduces boilerplate** → cleaner business logic
* **Encourages loose coupling** → easier to change components
* **Boosts reusability & testability**
* **Aligns with enterprise architecture best practices**

---

## 7. Where to Learn More

* 📖 *Spring Framework Reference Documentation*
* 📖 *Spring Boot Reference Guide*
* 📖 *Spring in Action* (Craig Walls)
* 🌍 Tutorials: Baeldung, Spring.io guides, GeeksforGeeks
* 🎓 Practice: Build microservices, event-driven apps, and REST APIs with Spring Boot

---
