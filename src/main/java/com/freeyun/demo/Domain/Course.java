package com.freeyun.demo.Domain;

import javax.persistence.*;

@Entity
public class Course {
    @Id @Column(length = 15) protected String cno;
    private String cname;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    public Course(){

    }
    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
