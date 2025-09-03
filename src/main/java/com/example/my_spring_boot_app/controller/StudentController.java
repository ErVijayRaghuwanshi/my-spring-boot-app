package com.example.my_spring_boot_app.controller;

import java.util.List;

import com.example.my_spring_boot_app.dto.StudentCreateDto;
import com.example.my_spring_boot_app.model.Student;
import com.example.my_spring_boot_app.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tags({@Tag(name = "Student Operations", description = "Operations related to students")})
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Define endpoints for student operations here
    
    
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // @PostMapping
    // public Student createStudent(@RequestBody Student student) {
    //     return studentService.createStudent(student);
    // }

    @PostMapping
    public Student createStudent(@RequestBody StudentCreateDto studentDto) {
        // Convert the DTO to a JPA entity
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        student.setCourse(studentDto.getCourse());

        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentCreateDto studentDto) {
        // Convert the DTO to a JPA entity
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        student.setCourse(studentDto.getCourse());

        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
