package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CourseRestController {
    @Autowired
    CourseService courseService;
    private final static Logger logger = LoggerFactory.getLogger(CourseRestController.class);

    @GetMapping(value = "/getCourseInfo")
    public Object GetCourseList(@RequestParam Integer page)
    {

      Page<Course> courses = courseService.GetCourseList(page);
      return  courses;
    }
    @PostMapping("/addCourseInfo")
    public int addCourseInfo(Course course,@RequestParam String tid)
    {
        int status = courseService.addCourseInfo(course,tid);
        return status;
    }
    @PostMapping("/deleCourseInfo")
    public  int deleInfo(@RequestParam String cno)
    {
        int status = courseService.deleInfo(cno);
        return status;
    }
    @PostMapping("/updateCourseInfo")
    public int updateCourseInfo(Course course,@RequestParam String tid){
        int status = courseService.updateCourseInfo(course,tid);
        return status;
    }


}
