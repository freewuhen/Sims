package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowInfoController {
    @Autowired
    private StudentRespository studentRepository;

    @GetMapping(value = "/getInfo")
    public List<Student> GetStudentList() {
          List<Student> students = studentRepository.findAll();
        return students;
    }
}
