package com.example.my_spring_boot_app.controller;

import com.example.my_spring_boot_app.dto.BookCreateDto;    // Import the new DTO
import com.example.my_spring_boot_app.model.Book;
import com.example.my_spring_boot_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // @PostMapping
    // public Book createBook(@RequestBody Book book) {
    //     return bookService.saveBook(book);
    // }

    @PostMapping
    public Book createBook(@RequestBody BookCreateDto bookDto) {
        // Convert the DTO to a JPA entity
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());

        return bookService.saveBook(book);
    }
}   