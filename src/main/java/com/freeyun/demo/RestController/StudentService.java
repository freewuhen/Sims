package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.NoSuchElementException;



@RestController
public class StudentService {
    @Autowired
    private StudentRespository studentRepository;
    @Autowired
    private StudentClassRespository classRespository;
    @GetMapping(value = "/getInfo")
    public Object GetStudentList(@RequestParam Integer page)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"sno");

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Student> students = studentRepository.findAll(pageable);
        return  students;
    }
    @PostMapping("/updateInfo")
    public int updateInfo(Student_info student_info)
    {
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
    @PostMapping("/addInfo")
    public int addInfo(Student_info student_info)
    {

        Boolean student_exist = true;
        Student testStudent;
        try{

            testStudent = studentRepository.findById(student_info.getSno()).get();
        }catch (NoSuchElementException e){
            student_exist = false;
        }
        if (student_exist)
        {
            return 3;
        }

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
    @GetMapping("/getStudentBysno")
    Student getQueryPageBysno(@RequestParam String sno)
    {
        Student student;
        try{
            student =studentRepository.findById(sno).get();
        }
        catch (NoSuchElementException e)
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
        student = studentRepository.findDistinctBySname(sname);
        if(student == null){ //自己写的查询方法，查不到时并不会抛异常
            System.out.print("\n"+"Exception"+"\n");
            student = new Student();
            student.setSno("error");
            return student;// 如果所查学生不存在则返回空值
        }
        return student;
    }
}
