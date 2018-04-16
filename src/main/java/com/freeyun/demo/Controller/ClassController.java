package com.freeyun.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassController {
    @GetMapping(value = {"/setclass","/setclass.html"})
    String setclass()
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            return "/setclass";
        }
        return "redirect:/signin";
    }
}
