package com.freeyun.demo.Service;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.ScoreRespository;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
@Service
public class StudentService {
    @Autowired
    private StudentRespository studentRepository;
    @Autowired
    private StudentClassRespository classRespository;
    @Autowired
    private ScoreRespository scoreRespository;
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    @GetMapping(value = "/getInfo")
    public  Page<Student> GetStudentList(@RequestParam Integer page)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"sno");//asc 顺序

        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Student> students = studentRepository.findAll(pageable);
        return  students;
    }
    @PostMapping("/updateInfo")
    public int updateStudent(Student_info student_info)
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
    public int addStudent(Student_info student_info)
    {

        Boolean student_exist = true;
        Student testStudent;
        try{

            testStudent = studentRepository.findById(student_info.getSno()).get();
        }catch (NoSuchElementException e){
            student_exist = false;
        }
        if (student_exist)//添加的学生已经存在
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
    @Transactional
    public  int deleStudent(@RequestParam String sno)
    {
        logger.error("String sno"+sno);
        try {
            Student student = studentRepository.findById(sno).get();
            scoreRespository.deleteBySnoIgnoreCase(sno);
            studentRepository.delete(student);
            return 1;
        }catch (Exception e)
        {
            logger.error(e.getLocalizedMessage());
            return 0;
        }

    }
    @GetMapping("/getStudentBysno")
    public Student getQueryPageBysno(String sno)
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
    public Student getQueryPageBysname(String sname)
    {

        Student student;
        student = studentRepository.findDistinctBySname(sname);
        if(student == null){ //自己写的查询方法，查不到时并不会抛异常
            student = new Student();
            student.setSno("error");
            return student;// 如果所查学生不存在则返回空值
        }
        return student;
    }
}
