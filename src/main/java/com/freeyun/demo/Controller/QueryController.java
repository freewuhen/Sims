package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {
    @Autowired private StudentRespository studentRespository;
    @GetMapping("/getStudentBysno")
    Student getQueryPage(@RequestParam String sno)
    {

        Student student = studentRespository.findById(sno).get();


        return student;
    }
}
