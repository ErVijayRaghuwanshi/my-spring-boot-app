
# 📘 Java Application Design Patterns – Detailed Documentation

## 1. Introduction

Design patterns are **proven, reusable solutions** to common software design problems. They provide developers with templates that simplify development, improve maintainability, and encourage **best practices in object-oriented programming (OOP)**.

In Java applications, design patterns play a crucial role in building **scalable, robust, and loosely coupled systems**. Many Java frameworks (e.g., Spring, Hibernate, Jakarta EE) integrate these patterns deeply in their architecture.

---

## 2. Categories of Design Patterns

The classic **Gang of Four (GoF)** book classifies patterns into three main categories:

1. **Creational Patterns** → Object creation mechanisms
2. **Structural Patterns** → Composition and organization of classes/objects
3. **Behavioral Patterns** → Communication between objects

---

## 3. Creational Patterns

These patterns abstract the instantiation process, making systems independent of how objects are created.

### 3.1 Singleton

* **Definition**: Ensures only one instance of a class exists and provides a global access point.
* **Java Use Case**: `Runtime.getRuntime()` or database connection pool managers.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {} // private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

### 3.2 Factory Method

* **Definition**: Provides an interface for creating objects but lets subclasses decide which class to instantiate.
* **Java Use Case**: `Calendar.getInstance()`

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() { System.out.println("Drawing Circle"); }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if ("circle".equalsIgnoreCase(type)) return new Circle();
        return null;
    }
}
```

---

### 3.3 Abstract Factory

* **Definition**: Produces families of related objects without specifying their concrete classes.
* **Java Use Case**: `javax.xml.parsers.DocumentBuilderFactory`

---

### 3.4 Builder

* **Definition**: Simplifies the construction of complex objects step by step.
* **Java Use Case**: `StringBuilder`, `Lombok @Builder`

```java
class User {
    private String name;
    private int age;

    static class Builder {
        private String name;
        private int age;

        Builder setName(String name) { this.name = name; return this; }
        Builder setAge(int age) { this.age = age; return this; }
        User build() { return new User(name, age); }
    }

    private User(String name, int age) { this.name = name; this.age = age; }
}
```

---

### 3.5 Prototype

* **Definition**: Creates new objects by copying an existing object (clone).
* **Java Use Case**: `Object.clone()`

---

## 4. Structural Patterns

These deal with object composition, focusing on **relationships between entities**.

### 4.1 Adapter

* **Definition**: Converts the interface of a class into another interface expected by clients.
* **Java Use Case**: `java.util.Arrays.asList()`

---

### 4.2 Proxy

* **Definition**: Provides a placeholder or surrogate for another object to control access.
* **Java Use Case**: RMI, Hibernate lazy loading proxies.

---

### 4.3 Decorator

* **Definition**: Dynamically adds behavior to objects.
* **Java Use Case**: `java.io.BufferedReader`, `Collections.unmodifiableList()`

---

### 4.4 Facade

* **Definition**: Provides a simplified interface to a complex subsystem.
* **Java Use Case**: `javax.faces.context.FacesContext`

---

### 4.5 Composite

* **Definition**: Treats individual objects and compositions of objects uniformly.
* **Java Use Case**: Swing UI components (`JPanel`, `JButton`).

---

## 5. Behavioral Patterns

These patterns focus on communication and responsibility assignment among objects.

### 5.1 Strategy

* **Definition**: Encapsulates algorithms and makes them interchangeable.
* **Java Use Case**: `Comparator` in sorting.

```java
class BubbleSort implements SortStrategy {
    public void sort(int[] numbers) { /* bubble sort logic */ }
}
class QuickSort implements SortStrategy {
    public void sort(int[] numbers) { /* quick sort logic */ }
}
```

---

### 5.2 Observer

* **Definition**: One-to-many dependency; when one object changes, dependents are notified.
* **Java Use Case**: `java.util.Observer`, Event-driven systems.

---

### 5.3 Template Method

* **Definition**: Defines the skeleton of an algorithm, allowing subclasses to override steps.
* **Java Use Case**: `java.util.AbstractList`, JDBC template classes.

---

### 5.4 Command

* **Definition**: Encapsulates requests as objects, allowing for queuing, logging, or undo operations.
* **Java Use Case**: `Runnable`, `ExecutorService.submit()`

---

### 5.5 Chain of Responsibility

* **Definition**: Passes requests along a chain until handled.
* **Java Use Case**: Servlet filters, logging frameworks.

---

### 5.6 State

* **Definition**: Allows objects to change behavior when their internal state changes.
* **Java Use Case**: Thread states in `Thread.State` enum.

---

## 6. Benefits of Using Design Patterns

* Promote **code reuse and maintainability**
* Enable **loose coupling** and flexibility
* Improve **testability**
* Provide **common vocabulary** for developers

---

## 7. Where to Learn More

* 📖 *Design Patterns: Elements of Reusable Object-Oriented Software* (GoF)
* 📖 *Head First Design Patterns* (Java-focused)
* 🌍 Tutorials: Baeldung, GeeksforGeeks, Refactoring.Guru
* 🛠 Practice: Implement patterns in small projects and gradually apply them to enterprise-level applications

