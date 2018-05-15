package com.freeyun.demo.RestController;

import com.freeyun.demo.Aspect.VerificationAspect;
import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.ScoreRespository;
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
    public int addCourseInfo(Course course)
    {
        Course course_tset = new Course();
        try {
            course_tset = courseRespository.findById(course.getCno()).get();
        }
        catch (NoSuchElementException e)
        {
            courseRespository.save(course);
            return 1;
        }
        return 0;

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
    public int updateCourseInfo(Course course) {
        Course course_tset = new Course();
        try {
            course_tset = courseRespository.findById(course.getCno()).get();
        }
        catch (NoSuchElementException e)
        {
            return 0;
        }

        courseRespository.save(course);
        return 1;

    }


}
