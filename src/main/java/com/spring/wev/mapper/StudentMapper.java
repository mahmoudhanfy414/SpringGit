package com.spring.wev.mapper;

import com.spring.wev.dto.StudentDto;
import com.spring.wev.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "myFirst", target = "name")
    Student convertFromStudentDtoToStudent(StudentDto studentDto);
    @Mapping(source = "name", target = "myFirst")
    StudentDto convertFromStudentToStudentDto(Student student);

    @Mapping(source = "name", target = "myFirst")
    List<StudentDto> convertFromStudentListToStudentDtoLis(List<Student> students);
    List<Student> convertFromStudentDtoListToStudentList(List<StudentDto> students);
}
