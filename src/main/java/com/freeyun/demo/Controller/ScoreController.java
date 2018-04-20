package com.freeyun.demo.Controller;

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

@Controller
public class ScoreController {
    @Autowired private StudentRespository studentRespository;
    @Autowired private CourseRespository courseRespository;
    @Autowired private ScoreRespository scoreRespository;
    private String sid;
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
    @GetMapping(value = {"/findCourseScore.html","/findCourseScore"})
    String getFindCourseScorePage(){
        return "/findCourseScore";
    }
    @PostMapping(value = {"/findStudentScore.html","/findStudentScore"})
    String postFindStudentScore(@RequestParam String sno){
        sid = sno;
        return "redirect:/findStudentScoreR";
    }
    @GetMapping(value = {"/findStudentScoreR","/findStudentScoreR.html"})
    String getFindStudentScoreResultPage(Model model){

        Student student = studentRespository.findById(sid).get();
        System.out.print("student.getSname():"+student.getSname()+"\n");
        List<Scores> scores = scoreRespository.findDistinctByStudentIgnoreCase(student);
        for (int i = 0; i < scores.size();i++)
        {
            System.out.print("scores.get(i).getScore():"+scores.get(i).getScore()+"\n");

        }
        model.addAttribute("scores",scores);
        return "/findStudentScoreR";
    }
}
