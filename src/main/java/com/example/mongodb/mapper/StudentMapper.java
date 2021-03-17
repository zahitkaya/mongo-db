package com.example.mongodb.mapper;

import com.example.mongodb.decorator.StudentDecorator;
import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import com.example.mongodb.domain.Student;
import org.mapstruct.*;
import org.springframework.validation.annotation.Validated;

@Mapper
@DecoratedWith(StudentDecorator.class)
public interface StudentMapper {
    Student studentRequestToStudentEntity(StudentRequestDto request);
    StudentDto studentEntityToStudentDto(Student student);
    StudentDto studentRequestToStudentResponse(StudentRequestDto request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void studentRequestToStudentResponse(StudentRequestDto requestDto, @MappingTarget Student student);


}
