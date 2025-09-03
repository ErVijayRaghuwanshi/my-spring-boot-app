package com.example.my_spring_boot_app.repository;

import com.example.my_spring_boot_app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA automatically provides CRUD methods.
    // You can also define custom query methods here, like:
    // List<Book> findByAuthor(String author);
}