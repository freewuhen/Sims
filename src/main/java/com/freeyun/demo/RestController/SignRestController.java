package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@RestController
public class SignRestController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/postSignin")
    public int signin(Admin admin, HttpSession session){
        int stscode = adminService.signin(admin,session);
        return stscode;
    }
    @PostMapping("/postSignup")
    public int signup(Admin admin){
        int stscode = adminService.signup(admin);
        return stscode;
    }
}
