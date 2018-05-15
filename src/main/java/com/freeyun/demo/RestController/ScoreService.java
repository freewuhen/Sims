package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.*;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.ScoreRespository;
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
public class ScoreService {
    @Autowired
    private ScoreRespository scoreRespository;
    @Autowired
    private StudentRespository studentRespository;
    @Autowired
    private CourseRespository courseRespository;

    private Scores deleteScore;
    Boolean ScoreExist(String sno,String cno)
    {
        ScoreMultiKeys scoreMultiKeys = new ScoreMultiKeys();
        scoreMultiKeys.setSno(sno);
        scoreMultiKeys.setCno(cno);
        try{

            deleteScore = scoreRespository.findById(scoreMultiKeys).get();
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
    @GetMapping(value = "/getScoreInfoBySno")
    public Object getScoreInfoBySno(@RequestParam Integer page,@RequestParam String sno)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"student_sno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Object[]> score_infos= scoreRespository.findBySnoIgnoreCase(sno,pageable);
        return  score_infos;
    }

    @GetMapping(value = "/getScoreInfoByCno")
    public Object getScoreInfoByno(@RequestParam Integer page,@RequestParam String cno)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"course_cno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Object[]> score_infos = scoreRespository.findByCnoIgnoreCase(cno,pageable);
        return  score_infos;
    }
    @PostMapping("/addScoreInfo")
    public int addScoreInfo(Scores scores)
    {
        Student student ;
        Course course ;
        String sno = scores.getSno(),cno = scores.getCno();
        Integer score = scores.getScore();
        try{
            student = studentRespository.findById(sno).get();
        }
        catch (NoSuchElementException e)// 捕捉到搜索异常
        {
            return 2; //输入的学号不存在

        }
        try {
            course = courseRespository.findById(cno).get();
        }catch (NoSuchElementException e){
            return 3;//输入的课程号不存在
        }

        if(score < 0 || score > 100)
        {
            return 4; // 成绩不合法
        }

        if(ScoreExist(sno,cno))
        {
            return 5; // 插入的成绩以存在，只能进行修改操作
        }



        scores.setStudent(student);
        scores.setCourse(course);
        scoreRespository.save(scores);

        return 1;

    }

    @PostMapping("/deleScoreInfo")
    public  int deleScoreInfo(@RequestParam String sno,@RequestParam String cno)
    {
        if(ScoreExist(sno,cno))
        {
            scoreRespository.delete(deleteScore);
            return 1;
        }
        else {
            return 0;
        }

    }

    @PostMapping("/updateScoreInfo")
    public int updateScoreInfo(Scores scores)
    {
        Student student ;
        Course course ;
        String sno = scores.getSno(),cno = scores.getCno();
        Integer score = scores.getScore();
        try{
            student = studentRespository.findById(sno).get();
        }
        catch (NoSuchElementException e)// 捕捉到搜索异常
        {
            return 2; //输入的学号不存在

        }
        try {
            course = courseRespository.findById(cno).get();
        }catch (NoSuchElementException e){
            return 3;//输入的课程号不存在
        }

        if(score < 0 || score > 100)
        {
            return 4; // 成绩不合法
        }
        scores.setStudent(student);
        scores.setCourse(course);
        scoreRespository.save(scores);

        return 1;

    }
}

