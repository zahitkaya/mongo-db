package com.example.mongodb.decorator;

import com.example.mongodb.controller.rest.StudentController;
import com.example.mongodb.domain.Student;
import com.example.mongodb.mapper.StudentMapper;
import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class StudentDecorator implements StudentMapper {
    @Setter(onMethod = @__({@Autowired}))
    StudentMapper studentMapper;

    @Override
    public StudentDto studentEntityToStudentDto(Student student) {
        var studentDto=studentMapper.studentEntityToStudentDto(student);
        Link[] links = new Link[]{
                linkTo(methodOn(StudentController.class).createStudent(null))
                        .withRel("student")
                        .withType("POST")
                        .withDeprecation("Add Student"),
                linkTo(methodOn(StudentController.class).findAllStudents(null))
                        .withRel("student")
                        .withType("GET")
                        .withDeprecation("List Students"),
                linkTo(methodOn(StudentController.class).updateStudent(0,null))
                        .withRel("student")
                        .withType("PUT")
                        .withDeprecation("Update Student")
        };

        studentDto.add(links);
        return studentDto;
    }

    @Override
    public Student studentRequestToStudentEntity(StudentRequestDto request) {
        var responseStudent=studentMapper.studentRequestToStudentEntity(request);
        Date birthday=request.getBirthday();
        var localBirtday= Instant.ofEpochMilli(birthday.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        var age=ChronoUnit.YEARS.between(localBirtday, LocalDate.now());
        responseStudent.setAge((int)age);


        return responseStudent;
    }
}
