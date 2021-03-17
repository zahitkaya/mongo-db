package com.example.mongodb.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class StudentRequestDto {

    @NotBlank(message = "name must not be empty")
    @Size(max = 50,min = 2)
    String name;

    @NotBlank(message = "surname must not be empty")
    @Size(max = 50,min = 2)
    String surname;

    @Email(message = "Email format is incorrect")
    String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past
    Date birthday;

}
