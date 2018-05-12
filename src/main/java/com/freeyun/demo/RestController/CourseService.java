package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Respository.CourseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseService {
    @Autowired
    CourseRespository courseRespository;
    @GetMapping(value = "/getCourseInfo")
    public Object GetCourseList(@RequestParam Integer page)
    {

        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"sno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Course> courses = courseRespository.findAll(pageable);
        return  courses;
    }
    @PostMapping("/addCourseInfo")
    public int addCourseInfo(Course course)
    {
        Course course_tset = new Course();
        course_tset = courseRespository.findDistinctByCname(course.getCname());
        if(course_tset == null)//classname existed
        {
            courseRespository.save(course);
            return 1;
        }
        else{
            return 0;
        }
    }
    @PostMapping("/deleCourseInfo")
    public  int deleInfo(@RequestParam String cno)
    {
        Course Course = courseRespository.findById(cno).get();
        courseRespository.delete(Course);
        return 1;
    }

}
