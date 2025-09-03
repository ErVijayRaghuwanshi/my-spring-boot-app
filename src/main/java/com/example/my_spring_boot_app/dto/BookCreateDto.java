package com.example.my_spring_boot_app.dto;

public class BookCreateDto {

    private String title;
    private String author;

    // Constructors, getters, and setters
    // (Lombok can make this even cleaner with @Data)
    public BookCreateDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}