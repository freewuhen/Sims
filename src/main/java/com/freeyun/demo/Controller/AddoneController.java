package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.StudentClassRespository;
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
    @Autowired private StudentClassRespository classRespository;

    @GetMapping("/addStudent.html")
    public String GetAddStudent()
    {
        return "addStudent";
    }
    @PostMapping ("/addStudent.html")
    public String PostAddStudent(Student_info student_info){

        Student student = new Student();
        student.setSno(student_info.getSno());
        student.setSname(student_info.getSname());
        student.setSage(student_info.getSage());
        student.setSsex(student_info.getSsex());

        StudentClass cls = classRespository.findDistinctByClassname(student_info.getClassname());
        if (cls == null) // classnmae is not exist
        {
            return "/error";
        }
        student.setStudentclass(cls);

        studentRepository.save(student);// add new student info to the database
        return "/ok";// return result webpage
    }
}
