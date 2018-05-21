package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.*;
import com.freeyun.demo.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreRestController {
    @Autowired
    ScoreService scoreService;
    private Scores deleteScore;

    @GetMapping(value = "/getScoreInfoBySno")
    public Object getScoreInfoBySno(@RequestParam Integer page,@RequestParam String sno)
    {
        Page<Object[]> scores = scoreService.getScoreInfoBySno(page,sno);
        return scores;
    }

    @GetMapping(value = "/getScoreInfoByCno")
    public Object getScoreInfoByno(@RequestParam Integer page,@RequestParam String cno)
    {
        Page<Object[]> scores = scoreService.getScoreInfoByCno(page,cno);
        return scores;
    }
    @PostMapping("/addScoreInfo")
    public int addScoreInfo(Scores scores)
    {
        int status = scoreService.addScoreInfo(scores);
        return status;
    }
    @PostMapping("/deleScoreInfo")
    public  int deleScoreInfo(@RequestParam String sno,@RequestParam String cno)
    {
        int status = scoreService.deleScoreInfo(sno,cno);
        return status;
    }

    @PostMapping("/updateScoreInfo")
    public int updateScoreInfo(Scores scores)
    {
        int status = scoreService.updateScoreInfo(scores);
        return status;
    }
}

