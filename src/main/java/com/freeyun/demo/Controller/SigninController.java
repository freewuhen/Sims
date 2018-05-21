package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Respository.AdminRespository;
import com.freeyun.demo.Service.AdminService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@Logger
public class SigninController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SigninController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/signin","/signin.html"})
    String testsign()
    {
        return "signin";
    }

    @PostMapping("/signin")
    public String sign(Admin admin, HttpSession session){
        int stscode = adminService.signin(admin,session);
        switch (stscode){
            case 1:
                return "redirect:index";
            case 0:
                return "redirect:error";
            case -1:
                return "redirect:error";
            case -2:
                return "redirect:error";

        }
        return "";

    }
}
