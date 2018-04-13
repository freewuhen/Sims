package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Respository.AdminRespository;
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
    private AdminRespository adminRespository;

    @GetMapping(value = {"/signin","/signin.html"})
    String testsign()
    {
        return "/signin";
    }

    @PostMapping("/signin")
    public String sign(Admin admin, HttpSession session){
        logger.info("signin start");
        Admin findadmin = adminRespository.findById(admin.getUsername()).get();
        logger.info("findadmin is ok");
        if(findadmin != null){//username is right
            logger.info("into the if");
            System.out.print("username is right\n");
            if(findadmin.getPassword().equals( admin.getPassword())){//password is right too
                System.out.print("password is right\n");
                session.setAttribute("Admin",admin.getUsername());
                return "redirect:/index";
            }
            else{
                System.out.print("password is error\n");
                return "redirect:/error";
            }
        }
        else{
            logger.info("into the else");
            System.out.print("username is error\n");
            return "/error";
        }

    }
}
