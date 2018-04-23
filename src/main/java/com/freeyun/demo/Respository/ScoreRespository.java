package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Scores;
import com.freeyun.demo.Domain.ScoreMultiKeys;
import com.freeyun.demo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRespository extends JpaRepository<Scores,ScoreMultiKeys> {
    @Query("select s from Scores s where s.sno = ?1")
    List<Scores> findDistinctBySnoIgnoreCase(String sno);
    //IgnoreCase 忽略组合主键的另一个条件 按组合主键其中一个进行查询 需要和 @Query 注解配合使用

    @Query("select s from Scores s where s.cno= ?1")
    List<Scores> findDistinctByCnoIgnoreCase(String cno);

}
