package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShowallController {
    @Autowired
    private StudentRespository studentRepository;
    @GetMapping("/showall.html")
    String GetStudentList(Model model){
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            List<Student> Students = studentRepository.findAll();
            model.addAttribute("students",Students);
            return  "/showall";
        }
        return "redirect:/signin";

    }


}
