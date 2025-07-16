package com.spring.wev.service;

import com.spring.wev.dto.StudentDto;
import com.spring.wev.model.Student;
import jakarta.transaction.SystemException;

import java.util.List;

public interface StudentService {

    void addStudent(StudentDto student) throws SystemException;

    StudentDto getStudentById(Long id);
    void removeStudentById(Long id);

    void updateStudent(Student student);

    List<StudentDto> getAllStudent();

    List<StudentDto> getStudentLikeLetter(String letters);
    List<StudentDto> getStudentByName(String name);
    List<StudentDto> getStudentByAge(int age);
}
