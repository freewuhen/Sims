package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Teacher;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.TeacherRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
public class TeacherService {
    @Autowired
    private  TeacherRespository teacherRespository;
    @Autowired
    private  CourseRespository courseRespository;
    private final static Logger logger = LoggerFactory.getLogger(TeacherService.class);
    @GetMapping("/getTeacherList")
    public Object getTeacherList(@RequestParam Integer page){
        Integer pagesize = 10;
        Sort sort = new Sort(Sort.Direction.ASC,"tno");
        Pageable pageable = PageRequest.of(page,pagesize,sort);
        Page<Teacher> teachers = teacherRespository.findAll(pageable);
        return teachers;
    }
    @PostMapping("/addTeacher")
    public int addTeacher(Teacher teacher)
    {
        Teacher testTeacher ;
        try {
            testTeacher = teacherRespository.findById(teacher.getTno()).get();
        }catch (Exception e)
        {
            teacherRespository.save(teacher);
            return 1;
        }
        return  0;
    }
    @PostMapping("/delteTeacher")
    @Transactional
    public int delteTeacher(@RequestParam String tno)
    {
        Teacher teacher;
        try{
            teacher = teacherRespository.findById(tno).get();
            courseRespository.deleteByTeacher(teacher);
            teacherRespository.delete(teacher);
            return 1;
        }
        catch (Exception e)
        {
            logger.error(e.getLocalizedMessage());
            return 0;
        }
    }
    @PostMapping("/updateTeacher")
    public int updateTeacher(Teacher teacher)
    {
        Teacher testTeacher ;
        try {
            testTeacher = teacherRespository.findById(teacher.getTno()).get();
            teacherRespository.save(teacher);
            return 1;
        }catch (Exception e)
        {
            logger.error(e.getLocalizedMessage());
            return  0;
        }

    }

}
