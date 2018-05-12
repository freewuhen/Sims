package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Score_info;
import com.freeyun.demo.Domain.Scores;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.ScoreRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreService {
    @Autowired
    private ScoreRespository scoreRespository;
    @GetMapping(value = "/getScoreInfo")
    //public Object GetScoreList(@RequestParam Integer page,@RequestParam String sno)
    public Object GetScoreList()
    {
        Integer page = 0;
        String sno = "20170302001";
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"student_sno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Object[]> score_infos= scoreRespository.findBySnoIgnoreCase(sno,pageable);
        return  score_infos;
    }
//    @PostMapping("/addScoreInfo")
//    public int addInfo(StudentClass studentClass)
//    {
//        StudentClass cls = new StudentClass();
//        cls = studentClassRespository.findDistinctByClassname(studentClass.getClassname());
//        if(cls == null)//classname existed
//        {
//            studentClassRespository.save(studentClass);
//            return 1;
//        }
//        else{
//            return 0;
//        }
//    }
//    @PostMapping("/deleScoreInfo")
//    public  int deleClassInfo(@RequestParam Integer classno)
//    {
//        StudentClass studentClass = studentClassRespository.findById(classno).get();
//        studentClassRespository.delete(studentClass);
//        return 1;
//    }
}
