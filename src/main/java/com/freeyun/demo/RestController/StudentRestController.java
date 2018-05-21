package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;


/**
 * 提供关于学生信息的相关接口
 */
@RestController
public class StudentRestController {
    @Autowired StudentService studentService;
    private final static Logger logger = LoggerFactory.getLogger(StudentRestController.class);
    @GetMapping(value = "/getInfo")
    public Object GetStudentList(@RequestParam Integer page)
    {
        Page<Student> students =  studentService.GetStudentList(page);
        return students;
    }
    @PostMapping("/updateInfo")
    public int updateStudent(Student_info student_info){
        int status_code = studentService.updateStudent(student_info);
        return status_code;//返回状态码
    }
    @PostMapping("/addInfo")
    public int addStudent(Student_info student_info)
    {
        int status_code = studentService.addStudent(student_info);
        return status_code;//返回状态码
    }

    @PostMapping("/deleInfo")
    @Transactional
    public  int deleStudent(@RequestParam String sno)
    {
        int status_code = studentService.deleStudent(sno);
        return status_code;//返回状态码
    }
    @GetMapping("/getStudentBysno")
    public Student getQueryPageBysno(@RequestParam String sno)
    {
        Student student = studentService.getQueryPageBysno(sno);
        return student;//返回状态码
    }
    @GetMapping("/getStudentBysname")
    public Student getQueryPageBysname(@RequestParam String sname)
    {

        Student student = studentService.getQueryPageBysname(sname);
        return student;
    }
}
