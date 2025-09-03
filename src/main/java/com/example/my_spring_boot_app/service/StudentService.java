package com.example.my_spring_boot_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.my_spring_boot_app.model.Student;
import com.example.my_spring_boot_app.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        // Logic to retrieve all students
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        // Logic to retrieve a student by ID
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        // Logic to create a new student
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        // Logic to update an existing student
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        // Logic to delete a student
        studentRepository.deleteById(id);
    }
}
