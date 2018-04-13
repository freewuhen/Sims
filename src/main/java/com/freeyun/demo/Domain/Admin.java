package com.freeyun.demo.Domain;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity //数据库注解
public class Admin {//管理员类
    @Id @Size(min = 6,max = 20) private String username;
    @Size(min = 6,max = 20) private String password;
    public Admin(){

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
