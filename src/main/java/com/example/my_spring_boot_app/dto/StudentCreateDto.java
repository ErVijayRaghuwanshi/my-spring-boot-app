package com.example.my_spring_boot_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// No Jakarta Persistence annotations needed here
public class StudentCreateDto {

    @Schema(description = "The student's full name", example = "John Doe")
    private String name;

    @Schema(description = "The student's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "The student's phone number", example = "+1234567890")
    private String phone;

    @Schema(description = "The course the student is enrolled in", example = "Computer Science")
    private String course;

    // Constructors, getters, and setters
    // You can use Lombok's @Data annotation for a cleaner code
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}