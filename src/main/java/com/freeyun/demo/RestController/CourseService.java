package com.freeyun.demo.RestController;

import com.freeyun.demo.Aspect.VerificationAspect;
import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Teacher;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.ScoreRespository;
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

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@RestController
public class CourseService {
    @Autowired
    CourseRespository courseRespository;
    @Autowired
    ScoreRespository scoreRespository;
    @Autowired
    TeacherRespository teacherRespository;
    private final static Logger logger = LoggerFactory.getLogger(CourseService.class);

    @GetMapping(value = "/getCourseInfo")
    public Object GetCourseList(@RequestParam Integer page)
    {

        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"cno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Course> courses = courseRespository.findAll(pageable);
        return  courses;
    }
    @PostMapping("/addCourseInfo")
    public int addCourseInfo(Course course,@RequestParam String tid)
    {
        Teacher teacher;
        try {
            teacher = teacherRespository.findById(tid).get();
        }catch (Exception e)
        {
            return 2; //教师不存在
        }
        Course course_tset ;
        try {
            course_tset = courseRespository.findById(course.getCno()).get();
        }
        catch (NoSuchElementException e)//该课程还不存在
        {
            course.setTeacher(teacher);
            courseRespository.save(course);
            return 1;
        }
        return 0;//该课程已经存在

    }
    @PostMapping("/deleCourseInfo")
    @Transactional
    public  int deleInfo(@RequestParam String cno)
    {
        Course Course;
        try {
            Course = courseRespository.findById(cno).get();
        }catch (Exception e)
        {
            logger.error(e.getLocalizedMessage());
            return  0;
        }
        scoreRespository.deleteByCnoIgnoreCase(cno);
        courseRespository.delete(Course);

        return 1;
    }
    @PostMapping("/updateCourseInfo")
    public int updateCourseInfo(Course course,@RequestParam String tid) {
        Teacher teacher;
        try {
            teacher = teacherRespository.findById(tid).get();
        }catch (Exception e)
        {
            return 2; //教师不存在
        }
        Course course_tset ;
        try {
            course_tset = courseRespository.findById(course.getCno()).get();
            course.setTeacher(teacher);
            courseRespository.save(course);
            return 1;
        }
        catch (NoSuchElementException e)//该课程不存在
        {
            return 0;

        }

    }


}
