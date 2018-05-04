package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.StudentRespository;
import com.freeyun.demo.Verification.SignVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ShowallController {
    @Autowired
    private StudentRespository studentRepository;
    @GetMapping(value = {"/showall.html","/showall"})
    String GetStudentList(Model model){
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            List<Student> Students = studentRepository.findAll();
            model.addAttribute("students",Students);
            return  "showall";
        }
        return "redirect:signin";

    }

    @PostMapping("/showall.html")
    public String Update( Student student){

        studentRepository.save(student);

        return "showall";
    }


}
