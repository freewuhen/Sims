package com.freeyun.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class StudentClass  {
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
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
