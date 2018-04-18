package com.freeyun.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id private String cno;
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
