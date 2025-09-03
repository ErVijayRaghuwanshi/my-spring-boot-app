
package com.example.my_spring_boot_app.repository;

import com.example.my_spring_boot_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Spring Data JPA automatically provides CRUD methods.
    // You can also define custom query methods here, like:
    // List<Student> findByLastName(String lastName);
}