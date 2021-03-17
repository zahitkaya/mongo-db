package com.example.mongodb.service;

import com.example.mongodb.domain.Student;
import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentRequestDto student);
    public  Page<StudentDto> pullAllStudents(Pageable pageable);
    StudentDto updateStudent(String id,StudentRequestDto student);
     void deleteStudentById(String id);
    StudentDto getStudentById(String id);



}
