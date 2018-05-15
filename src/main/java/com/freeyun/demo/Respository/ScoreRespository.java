package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRespository extends JpaRepository<Scores,ScoreMultiKeys> {
  // @Query("select course.cname,scores.score from Scores scores inner join Course course  on scores.cno = course.cno where scores.sno = ?1")
//   List<Object[]> findDistinctBySnoIgnoreCase(String sno);
//    //IgnoreCase 忽略组合主键的另一个条件 按组合主键其中一个进行查询 需要和 @Query 注解配合使用
   @Query(value = "select course.cname,scores.score from course,scores where course.cno = scores.course_cno and student_sno = ?1",countQuery = "select count(*) FROM scores where student_sno = ?1",nativeQuery = true)
   Page<Object[]> findBySnoIgnoreCase(String student_sno,Pageable pageable);

    @Query(value = "select student.sname,scores.score from  scores,student   where scores.student_sno = student.sno and scores.course_cno = ?1",countQuery = "select count(*) FROM scores where course_cno = ?1",nativeQuery = true)
    Page<Object[]> findByCnoIgnoreCase(String course_cno,Pageable pageable);


    void deleteByCnoIgnoreCase(String cno);

    void deleteBySnoIgnoreCase(String sno);



}
