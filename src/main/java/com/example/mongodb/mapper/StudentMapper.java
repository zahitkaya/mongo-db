package com.example.mongodb.mapper;

import com.example.mongodb.decorator.StudentDecorator;
import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import com.example.mongodb.domain.Student;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(StudentDecorator.class)
public interface StudentMapper {
    Student studentRequestToStudentEntity(StudentRequestDto request);
    StudentDto studentEntityToStudentDto(Student student);
    StudentDto studentRequestToStudentResponse(StudentRequestDto request);
}
