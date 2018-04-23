package com.freeyun.demo.Domain;

import java.io.Serializable;
import java.util.Objects;

public class ScoreMultiKeys implements Serializable{

    protected String sno;
    protected String cno;

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
}
