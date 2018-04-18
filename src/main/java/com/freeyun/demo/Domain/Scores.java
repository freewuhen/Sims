package com.freeyun.demo.Domain;

import javax.persistence.*;

@Entity
@IdClass(ScoreMultiKeys.class)
public class Scores {
    @Id @ManyToOne Student student;
    @Id @ManyToOne Course course;
    private Integer score;
    public Scores(){

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
