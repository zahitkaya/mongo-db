package com.example.mongodb.controller.rest;

import com.example.mongodb.model.request.StudentRequestDto;
import com.example.mongodb.model.response.StudentDto;
import com.example.mongodb.domain.Student;
import com.example.mongodb.service.StudentService;
import com.example.mongodb.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("v1")
public class StudentController {
    final StudentService studentServiceImpl;

    @Operation(summary = "List students with using page")
    @PageableAsQueryParam
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok" ),
            @ApiResponse(responseCode = "400", description = "Bad Request" ) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    public Page<StudentDto> findAllStudents(@Parameter(hidden = true) Pageable pageable){
       return studentServiceImpl.pullAllStudents(pageable);
    }

    @Operation(summary = "Create new Student")
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok" ),
            @ApiResponse(responseCode = "400", description = "Bad Request" ) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    public StudentDto createStudent(@RequestBody @Valid StudentRequestDto student){
        return studentServiceImpl.createStudent(student);
    }

    @Operation(summary = "Delete student with id")
    @DeleteMapping("/students/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok" ),
            @ApiResponse(responseCode = "400", description = "Bad Request" ) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    public void deleteById(@PathVariable int id){
        studentServiceImpl.deleteStudentById(id);
    }

    @Operation(summary = "Get a student with student id")
    @GetMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok" ),
            @ApiResponse(responseCode = "400", description = "Bad Request" ) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    public StudentDto getStudent(@PathVariable int id){
        return studentServiceImpl.getStudentById(id);
    }

    @Operation(summary = "Update student informations with using id")
    @PutMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok" ),
            @ApiResponse(responseCode = "400", description = "Bad Request" ) ,
            @ApiResponse(responseCode = "404", description = "Student not found" ) ,
            @ApiResponse(responseCode = "500", description = "Internal Server Error")

    })
    public StudentDto updateStudent(@PathVariable int id,@RequestBody @Valid StudentRequestDto student){
        return studentServiceImpl.updateStudent(id,student);
    }

}
