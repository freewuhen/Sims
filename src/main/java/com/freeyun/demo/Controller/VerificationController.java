package com.freeyun.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerificationController {
    public String returnSignPage()
    {
        return "redirect:/signin";
    }
}
