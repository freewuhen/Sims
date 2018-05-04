package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Respository.AdminRespository;
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
    @GetMapping("signup.html")
    public String GetSignup()
    {
        return "/signup";
    }
    @PostMapping("signup.html")
    public String Signup(@Valid Admin admin, BindingResult bindingResult){
        if (bindingResult.hasErrors())// 如果用户名或者密码不符合规范
        {
            System.out.print("bindingResult.hasErrors()");
            return "error";
        }
        adminRespository.save(admin);
        return "redirect:signin";
    }
}
