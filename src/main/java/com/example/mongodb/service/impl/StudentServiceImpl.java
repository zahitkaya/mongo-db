package com.example.mongodb.service.impl;

import com.example.mongodb.exception.ResourceNotFoundException;
import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import com.example.mongodb.mapper.StudentMapper;
import com.example.mongodb.repository.StudentRepository;
import com.example.mongodb.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.ParameterizedType;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;


    @Override
    public StudentDto createStudent(StudentRequestDto student) {

        if (studentRepository.existsStudentByEmail(student.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already exist");

        var studentEntity= studentRepository.save(studentMapper.studentRequestToStudentEntity(student));
        log.info("Student added to db: " + student);
        return studentMapper.studentEntityToStudentDto(studentEntity);
    }

    @Override
    public  Page<StudentDto> pullAllStudents(Pageable pageable) {
        var studentList = studentRepository.findAll(pageable).map(studentMapper::studentEntityToStudentDto);
        return studentList;
    }

    @Override
    public StudentDto updateStudent(String id, StudentRequestDto requestDto) {
        var studentEntity = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Students", "id", id));
        studentMapper.studentRequestToStudentResponse(requestDto,studentEntity);
        studentRepository.save(studentEntity);
        return studentMapper.studentEntityToStudentDto(studentEntity);
    }

    @Override
    public void deleteStudentById(String id) {
        log.info("Student deleted");
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudentById(String id) {
        var student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Students", "id", id));
        return studentMapper.studentEntityToStudentDto(student);
    }

}
