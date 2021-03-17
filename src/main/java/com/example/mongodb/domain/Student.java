package com.example.mongodb.domain;

import com.example.mongodb.audit.Auditable;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Student")
public class Student extends Auditable<String> {
    @Id
    String id;

    @Field(name = "student_name")
    String name;
    String surname;;


    String email;
    Date birthday;
    int age;

}
