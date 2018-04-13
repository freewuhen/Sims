package com.freeyun.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/index","/index.html"})
    String Getindex()
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            return "/index";
        }
        return "redirect:/signin";
    }


}
