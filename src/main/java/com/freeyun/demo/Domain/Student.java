package com.freeyun.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student
{
    @Id private String sno;//以学号为主键
    private String sname;
    private Integer sage;
    private String ssex;
    public Student(){

    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;

    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}