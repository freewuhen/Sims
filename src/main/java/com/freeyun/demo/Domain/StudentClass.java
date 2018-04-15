package com.freeyun.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class StudentClass {
    @Id private Integer classno;
    private String classname;
    public StudentClass(){

    }

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
