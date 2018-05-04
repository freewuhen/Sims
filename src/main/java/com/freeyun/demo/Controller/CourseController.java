package com.freeyun.demo.Controller;


import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Verification.SignVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {
    @Autowired private CourseRespository courseRespository;

    @GetMapping(value = {"/setCourse","/setCourse.html"})
    String setCourse(Model model)//获取课程管理页面
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            List<Course> courses = courseRespository.findAll();
            model.addAttribute("courses",courses);
            return "setCourse";
        }
        return "redirect:signin";
    }
    @GetMapping(value = {"/addCourse","/addCourse.html"})
    String addCourse()
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            return "addCourse";
        }
        return "redirect:signin";
    }

    @PostMapping(value = {"/addCourse","/addCourse.html"})
    String addClass(Course course)
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            if (courseRespository.findDistinctByCname(course.getCname()) != null) {
                return "error";
            }

            courseRespository.save(course);

            return "ok";
        }
        return "redirect:signin";

    }


}
