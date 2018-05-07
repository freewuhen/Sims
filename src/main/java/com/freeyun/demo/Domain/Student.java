package com.freeyun.demo.Domain;

//import javax.persistence.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
public class Student
{
    @Id  @Column(length = 15) protected String sno;//以学号为主键
    private String sname;
    private Integer sage;
    private String ssex;
    @Column(length = 15) private String teleno;//手机号
    private String addr;//地址

    @ManyToOne
    @JoinColumn(name = "class_id")
    private StudentClass studentclass;

    public Student(){

    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;

    }

    public StudentClass getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(StudentClass studentclass) {
        this.studentclass = studentclass;
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

    public String getTeleno() {
        return teleno;
    }

    public void setTeleno(String teleno) {
        this.teleno = teleno;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}