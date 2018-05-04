package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Verification.SignVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClassController {
    @Autowired
    private StudentClassRespository studentClassRespository;

    @GetMapping(value = {"/setclass","/setclass.html"})
    String setClass(Model model)
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            List<StudentClass> classes = studentClassRespository.findAll();
            model.addAttribute("classes",classes);
            return "setclass";
        }
        return "redirect:signin";
    }
    @GetMapping(value = {"/addclass","/addclass.html"})
    String addClass()
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
        return "addclass";
        }
        return "redirect:signin";
    }

    @PostMapping(value = {"/addclass","/addclass.html"})
    String addClass(StudentClass studentClass)
    {
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            if (studentClassRespository.findDistinctByClassname(studentClass.getClassname()) != null) {
                return "error";
            }

            studentClassRespository.save(studentClass);

            return "ok";
        }
        return "redirect:signin";

    }

}
