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
    Student getQueryPageBysno(@RequestParam String sno)
    {
        Student student;
        try{
            student = studentRespository.findById(sno).get();
        }
        catch (Exception e)
        {
            student = new Student();
            student.setSno("error");
            return student;// 如果所查学生不存在则返回空值
        }


        return student;
    }
    @GetMapping("/getStudentBysname")
    Student getQueryPageBysname(@RequestParam String sname)
    {
        Student student;
        try{
            student = studentRespository.findDistinctBySname(sname);
        }
        catch (Exception e)
        {
            student = new Student();
            student.setSno("error");
            return student;// 如果所查学生不存在则返回空值
        }
        return student;
    }
}
