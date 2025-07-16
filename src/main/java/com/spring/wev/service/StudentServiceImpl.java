package com.spring.wev.service;

import com.spring.wev.Repository.StudentDao;
import com.spring.wev.dto.StudentDto;
import com.spring.wev.mapper.StudentMapper;
import com.spring.wev.model.Student;
import jakarta.transaction.SystemException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addStudent(StudentDto studentDto) throws SystemException {

        if (studentDto.getId() != null) {
            throw new SystemException("id must be null");
        }
        if (studentDto.getMyFirst() == null) {
            throw new SystemException("Name must be not null");
        }
        /*if (studentDto.getMyFirst().length() < 5) {
            throw new SystemException("name must be greater than 5");
        }*/

        Student stu = StudentMapper.studentMapper.convertFromStudentDtoToStudent(studentDto);
       // Student stu = modelMapper.map(studentDto, Student.class);
        /*
        Student studentEntity = new Student();
        */
        stu.setRegisterDate(LocalDateTime.now());
        studentDao.save(stu);
    }

    @Override
    public StudentDto getStudentById(Long id) {

        Optional<Student> optionalStudent= studentDao.findById(id);
        if (optionalStudent.isPresent())
        {
            Student s = optionalStudent.get();
            return StudentMapper.studentMapper.convertFromStudentToStudentDto(s);
        }
        else
        {
            throw new RuntimeException("STUDENT NOT FOUND WITH ID :"+ id);
        }

    }

    @Override
    public void removeStudentById(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.save(student);
    }

    @Override
    public List<StudentDto> getAllStudent() {
        //List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentDao.findAll();
        Double avg = studentDao.getAvgOfAge();

        return StudentMapper.studentMapper.convertFromStudentListToStudentDtoLis(students);
//        for (Student student: students) {
//            StudentDto studentDto = StudentMapper.studentMapper.convertFromStudentToStudentDto(student);
////            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
//            /*StudentDto studentDto = new StudentDto();
//            studentDto.setId(student.getId());
//            studentDto.setMyFirst(student.getName());
//            studentDto.setAge(student.getAge());
//            studentDto.setGPA(student.getGPA());
//            studentDto.setAvgOfAge(avg);
//            studentDto.setPassword(student.getPassword());*/
//
//            studentDtos.add(studentDto);
//        }

        //return studentDtos;
    }

    @Override
    public List<StudentDto> getStudentLikeLetter(String letters) {
        List<Student>students=studentDao.findByNameContains(letters);
        return StudentMapper.studentMapper.convertFromStudentListToStudentDtoLis(students);
    }

    @Override
    public List<StudentDto> getStudentByName(String name) {
        List<Student>students=studentDao.findByName(name);
        return StudentMapper.studentMapper.convertFromStudentListToStudentDtoLis(students);
    }

    @Override
    public List<StudentDto> getStudentByAge(int age) {
        List<Student>students=studentDao.findByAge(age);
        return StudentMapper.studentMapper.convertFromStudentListToStudentDtoLis(students);
    }


}
