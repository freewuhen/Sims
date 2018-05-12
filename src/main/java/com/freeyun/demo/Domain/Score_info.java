package com.freeyun.demo.Domain;

public class Score_info {
    public String cname;
    public Integer score;
    public Score_info(String name, Integer score)
    {
        this.cname = name;
        this.score = score;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
