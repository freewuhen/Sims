package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddoneController {
    //根据sno查询学生
    @Autowired
    private StudentRespository studentRepository;

    @GetMapping("/addStudent.html")
    public String GetAddStudent()
    {
        return "addStudent";
    }
    @PostMapping ("/addStudent.html")
    public String PostAddStudent(Student student){
        studentRepository.save(student);// add new student info to the database
        return "/ok";// return result webpage
    }
}
