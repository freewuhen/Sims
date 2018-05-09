package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import com.freeyun.demo.Verification.SignVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ShowInfoController {
    @Autowired
    private StudentRespository studentRepository;
    @Autowired
    private StudentClassRespository classRespository;
    @GetMapping(value = "/getInfo")
    public Object GetStudentList() {
        SignVerification v = new SignVerification();
        List<Student> students = studentRepository.findAll();
        return  students;
//        if(v.Verification()) {
//            students = studentRepository.findAll();
//            return students;
//        }
//        else {
//            students = new ArrayList<>(1);
//            Student student = new Student();
//            student.setSno("sign");
//            students.add(student);
//
//            return students;
//        }

    }
    @PostMapping("/updateInfo")
    public int updateInfo(Student_info student_info){

//        Boolean student_exit = true;
//        Student testStudent;
//        try{
//            testStudent = studentRepository.findById(student_info.getSno()).get();
//        }catch (NoSuchElementException e){
//            student_exit = false;
//        }
//        if (student_exit)
//        {
//            return 3;
//        }

        StudentClass cls = new StudentClass();
        try{
            cls = classRespository.findDistinctByClassname(student_info.getClassname());
        }catch (NoSuchElementException e)// 添加的班级不存在
        {
            return 2;
        }

        Student student = new Student();
        student.setSno(student_info.getSno());
        student.setSname(student_info.getSname());
        student.setSage(student_info.getSage());
        student.setSsex(student_info.getSsex());
        student.setAddr(student_info.getAddr());
        student.setTeleno(student_info.getTeleno());

        student.setStudentclass(cls);
        studentRepository.save(student);

        return 1;
    }
    @PostMapping("/deleInfo")
    public  int deleInfo(@RequestParam String sno)
    {
        Student student = studentRepository.findById(sno).get();
        studentRepository.delete(student);
        return 1;
    }
}
