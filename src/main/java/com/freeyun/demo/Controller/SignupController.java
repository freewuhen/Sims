package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Respository.AdminRespository;
import com.freeyun.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private AdminRespository adminRespository;
    @Autowired
    AdminService adminService;
    @GetMapping("signup.html")
    public String GetSignup()
    {
        return "/signup";
    }
    @PostMapping("signup.html")
    public String Signup( Admin admin){

        if (adminService.signup(admin) == 1)
        {
            return "redirect:signin";
        }
        return "error";

    }
}
