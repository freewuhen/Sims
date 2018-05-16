package com.freeyun.demo.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
    @Id private String tno;
    private String tname;

    public Teacher() {

    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
