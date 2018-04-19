package com.freeyun.demo.Domain;

import javax.persistence.*;

@Entity
public class Course {
    @Id @Column(length = 15) private String cno;
    private String cname;
    private String teacher;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
