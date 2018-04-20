package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Scores;
import com.freeyun.demo.Domain.ScoreMultiKeys;
import com.freeyun.demo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRespository extends JpaRepository<Scores,ScoreMultiKeys> {
    @Query("select s from Scores s where s.student= ?1")
    List<Scores> findDistinctByStudentIgnoreCase(Student student);
    List<Scores> findDistinctByCourse(Course c);

}
