
# 📘 Comprehensive Guide to Swagger/OpenAPI Annotations in Spring Boot

Swagger UI (powered by the **OpenAPI 3 specification**) allows developers to **visualize, explore, and test REST APIs** interactively. In **Spring Boot**, the `springdoc-openapi` library automatically generates this documentation from annotations in your controllers, models, and endpoints.

This guide covers the **most common OpenAPI annotations**, with practical **Java examples**.

---

## 1. Project Setup

Add the dependency to your **Maven** `pom.xml`:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version> <!-- check latest -->
</dependency>
```

For **Gradle**:

```gradle
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
```

👉 After adding this, Swagger UI will be available at:

```
http://localhost:8080/swagger-ui.html
```

---

## 2. Core Annotations

### 🔹 `@Tag`

Groups related endpoints together.

**Usage:** Class-level (on `@RestController`).

**Attributes:**

* `name` → Name of the tag (displayed as a section in Swagger UI).
* `description` → Short description of the group.

**Example:**

```java
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController { }
```

---

### 🔹 `@Operation`

Describes a single endpoint (method).

**Usage:** Method-level (on `@GetMapping`, `@PostMapping`, etc.).

**Attributes:**

* `summary` → Short description (headline).
* `description` → Longer explanation.
* `operationId` → Unique ID for the endpoint.
* `tags` → Assigns to specific tags.
* `deprecated` → Marks endpoint as deprecated.
* `responses` → Declares possible HTTP responses.

**Example:**

```java
@PostMapping
@Operation(
    summary = "Create a new product",
    description = "Adds a new product to the store database"
)
public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return ResponseEntity.ok(product);
}
```

---

### 🔹 `@Parameter`

Documents request parameters.

**Usage:** Method parameters.

**Attributes:**

* `name` → Parameter name.
* `description` → Explanation of purpose.
* `in` → Location (`query`, `path`, `header`, `cookie`).
* `required` → Whether it’s mandatory.
* `example` → Example value.
* `schema` → Data type details.

**Example:**

```java
@GetMapping("/{id}")
@Operation(summary = "Get product by ID")
public ResponseEntity<Product> getProductById(
    @Parameter(
        description = "Unique ID of the product",
        required = true,
        example = "123"
    )
    @PathVariable Long id
) {
    return ResponseEntity.ok(new Product());
}
```

---

### 🔹 `@ApiResponse` & `@ApiResponses`

Defines responses for an operation.

**Usage:** Nested in `@Operation`.

**Attributes:**

* `responseCode` → HTTP code (`200`, `400`, etc.).
* `description` → Meaning of the response.
* `content` → Response body type.

**Example:**

```java
@GetMapping("/{id}")
@Operation(
    summary = "Fetch product by ID",
    responses = {
        @ApiResponse(responseCode = "200", description = "Product retrieved"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    }
)
public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return ResponseEntity.ok(new Product());
}
```

---

### 🔹 `@Schema`

Describes data models (DTOs, Entities).

**Usage:** On class or fields.

**Attributes:**

* `description` → Explains field/class.
* `example` → Example value.
* `required` → If field must be present.

**Example:**

```java
public class Product {

    @Schema(description = "Unique product ID", example = "101")
    private Long id;

    @Schema(description = "Name of the product", example = "Laptop")
    private String name;

    @Schema(description = "Price in USD", example = "999.99")
    private Double price;
}
```

---

## 3. Combining Annotations (Full Example)

```java
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @PostMapping
    @Operation(
        summary = "Create a new user",
        description = "Accepts JSON payload and creates a user account",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "User created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User.class)
                )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
        }
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get user by ID",
        description = "Fetches a user by their unique identifier",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    public ResponseEntity<User> getUserById(
        @Parameter(description = "User ID", example = "42") @PathVariable Long id
    ) {
        return ResponseEntity.ok(new User());
    }
}
```

---

## 4. Other Useful Annotations

* **`@RequestBody`** → Document request payloads.
* **`@Content`** → Defines response/request body media types.
* **`@ArraySchema`** → Describes arrays in request/response.
* **`@Hidden`** → Hides an API or model field from documentation.
* **`@ExampleObject`** → Adds custom examples for request/response bodies.

**Example with `@ExampleObject`:**

```java
@PostMapping("/login")
@Operation(
    summary = "User login",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        content = @Content(
            mediaType = "application/json",
            examples = {
                @ExampleObject(
                    name = "Valid Login",
                    value = "{ \"username\": \"admin\", \"password\": \"1234\" }"
                )
            }
        )
    )
)
public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok("JWT_TOKEN");
}
```

---

## 5. Swagger UI Preview

Once configured, your documentation will appear in **Swagger UI** like this:

* Left sidebar → Tags (`Product Management`, `User Management`).
* Right panel → Endpoints (`GET /api/products/{id}`, `POST /api/users`).
* Try-it-out → Test APIs directly.

---

✅ With these annotations, you can generate **rich, interactive API docs** with minimal manual effort.

