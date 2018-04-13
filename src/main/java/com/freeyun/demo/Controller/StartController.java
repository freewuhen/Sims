package com.freeyun.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String hello()
    {
        return "redirect:/signin";
    }
    @GetMapping("/ok")
    public String ok() {
        return "/ok";
    }
}
