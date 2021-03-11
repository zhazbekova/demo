package com.school.demo.controller;


import com.school.demo.entity.Student;
import com.school.demo.entity.Teacher;
import com.school.demo.repo.StudentRepository;
import com.school.demo.repo.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Controller(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @PutMapping("/create-student/")
    public ResponseEntity<Student> createStudent(@RequestBody Student studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setGrade(studentDto.getGrade());
        student.setAddress(studentDto.getAddress());
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @PutMapping("/create-teacher/")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setSalary(teacherDto.getSalary());
        return new ResponseEntity<>(teacherRepository.save(teacher), HttpStatus.OK);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/student-id/{id}")
    public Student getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElse(null);
    }

    @GetMapping("/student-name/{name}")
    public Student getStudentByName(@PathVariable String name) {
        Optional<Student> studentOptional = studentRepository.findByName(name);
        return studentOptional.orElse(null);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.orElse(null);
    }



}
