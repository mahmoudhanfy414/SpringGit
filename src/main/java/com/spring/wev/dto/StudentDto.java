package com.spring.wev.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StudentDto {

    private Long id;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    //@NotBlank(message = "name must be not null")
    @Size(min = 5, message = "validation Error name must be a least 5 character")
    private String myFirst;

    //@Pattern(regexp = "", message = "")
    private String password;

    @Min(25)
    private int age;
    private Float gpa;
    private LocalDateTime registerDate;




}
