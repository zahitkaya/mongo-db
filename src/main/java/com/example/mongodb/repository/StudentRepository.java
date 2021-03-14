package com.example.mongodb.repository;

import com.example.mongodb.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StudentRepository extends MongoRepository<Student,Integer> {
    Boolean existsStudentByEmail(String email);
}
