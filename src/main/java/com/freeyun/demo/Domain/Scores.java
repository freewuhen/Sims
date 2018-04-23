package com.freeyun.demo.Domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@IdClass(ScoreMultiKeys.class)
public class Scores {
    @Id  @Column(name = "student_sno",length = 15)  protected   String sno;
    @Id  @Column(name = "course_cno",length = 15) protected String cno;
    @ManyToOne
    @JoinColumn(name = "student_sno",insertable = false, updatable = false)
    protected   Student student;
    @ManyToOne
    @JoinColumn(name = "course_cno",insertable = false, updatable = false) protected Course course;
    private Integer score;
    public Scores(){

    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
}
