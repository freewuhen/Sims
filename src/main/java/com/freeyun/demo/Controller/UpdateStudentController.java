package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStudentController {
    @Autowired StudentRespository studentRespository;
    @GetMapping("/updateStudent")
    String updateStudent(Student student)
    {
        return "ok";
    }
}
