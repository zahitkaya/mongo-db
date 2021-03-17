package com.example.mongodb.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto extends RepresentationModel<StudentDto> {
    String id;
    String name;
    String surname;;
    String email;
    int age;
}
