package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRespository extends JpaRepository<Scores,ScoreMultiKeys> {
   @Query("select course.cname,scores.score from Scores scores inner join Course course  on scores.cno = course.cno where scores.sno = ?1")
   List<Object[]> findDistinctBySnoIgnoreCase(String sno);
    //IgnoreCase 忽略组合主键的另一个条件 按组合主键其中一个进行查询 需要和 @Query 注解配合使用

    @Query("select student.sname,scores.score from Scores scores inner join Student student  on scores.sno = student.sno where scores.cno = ?1")
    List<Object[]> findDistinctByCnoIgnoreCase(String cno);

}
