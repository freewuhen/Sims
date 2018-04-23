package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Scores;
import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.ScoreRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ScoreController {
    @Autowired private StudentRespository studentRespository;
    @Autowired private CourseRespository courseRespository;
    @Autowired private ScoreRespository scoreRespository;
    private String sid;
    private String cid;

    @GetMapping(value = {"/ScoreController","/ScoreController.html"})
    String getScoreContollerPage(){
        return "/ScoreController";
    }
    @GetMapping(value = {"/findScore.html","/findScore"})
    String getFindScorePage(){
        return "/findScore";
    }
    @GetMapping(value = {"/findStudentScore.html","/findStudentScore"})
    String getFindStudentScorePage(){
        return "/findStudentScore";
    }
    @PostMapping(value = {"/findStudentScore.html","/findStudentScore"})
    String postFindStudentScore(@RequestParam String sno){
        sid = sno;
        return "redirect:/findStudentScoreR";
    }
    @GetMapping(value = {"/findStudentScoreR","/findStudentScoreR.html"})
    String getFindStudentScoreResultPage(Model model){
        try{
            Student student = studentRespository.findById(sid).get();
            System.out.print("student.getSname():"+student.getSname()+"\n");
            List<Scores> scores = scoreRespository.findDistinctBySnoIgnoreCase(sid);
            model.addAttribute("scores",scores);
            model.addAttribute("sname",student.getSname());
        }
        catch (NoSuchElementException e)
        {
            return "find_error";
        }


        return "/findStudentScoreR";
    }

    @GetMapping(value = {"/findCourseScore.html","/findCourseScore"})
    String getFindCourseScorePage(){
        return "/findCourseScore";
    }
    @PostMapping(value = {"/findCourseScore.html","/findCourseScore"})
    String postFindCourseScore(@RequestParam String cno){
        cid = cno;
        return "redirect:/findCourseScoreR";
    }
    @GetMapping(value = {"/findCourseScoreR","/findCourseScoreR.html"})
    String getFindCourseScoreResultPage(Model model){
        try{
            Course course = courseRespository.findById(cid).get();
            System.out.print("student.getSname():"+course.getCname()+"\n");
            List<Scores> scores = scoreRespository.findDistinctByCnoIgnoreCase(cid);
            model.addAttribute("scores",scores);
            model.addAttribute("sname",course.getCname());
        }
        catch (NoSuchElementException e)
        {
            return "find_error";
        }


        return "/findStudentScoreR";
    }
    @GetMapping(value = {"/addScore","/addScore.html"})
    String getAddCoursePage(){
        return "/addScore";
    }
    @PostMapping(value = {"/addScore","/addScore.html"})
    String postAddCoursePage(Scores scores){
        Student student = new Student();
        Course course = new Course();
        System.out.print("sno:"+scores.getSno()+"cno:"+scores.getCno()+"score:"+scores.getScore());
        try{
            student = studentRespository.findById(scores.getSno()).get();
            course = courseRespository.findById(scores.getCno()).get();
        }
        catch (NoSuchElementException e)// 捕捉到搜索异常
        {
            if(student == null){return "/find_error";} //输入的学号不存在
            if(course == null){return "/error";}//输入的课程号不存在
        }


        scores.setStudent(student);
        scores.setCourse(course);
        scoreRespository.save(scores);

        return "/ok";
    }
}
