package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageStudentController {//管理学生信息
    @Autowired
    private StudentRespository studentRepository;
    @Autowired
    private StudentClassRespository classrespository;
    private String id;
    private Student t_student;// 用来传递查找与更新
    //Find student
    private boolean isfind = false;

    @GetMapping(value = {"/findoneStudent.html","/findoneStudent"})
    public String GetFindoneStudent()
    {
        SignVerification v = new SignVerification();
        if(v.Verification()){

            return "/findoneStudent";
        }
        return "redirect:/signin";
   }
    @PostMapping(value = {"/findoneStudent.html","/findoneStudent"})
    public String PostFindoneStudent(@RequestParam String sno,Model model){
        id = sno;
        isfind = true;
        return("redirect:/findoneresult");
    }
    @GetMapping("/findoneresult")
    public String FindoneResult(Model model){
        SignVerification v = new SignVerification();
        if(v.Verification())
        {
            System.out.print("Verification pass");
            Student student = studentRepository.findById(id).get();
            StudentClass sclass = student.getStudentclass();
            String classname = sclass.getClassname();
            t_student = student;
            model.addAttribute("student",student);
            model.addAttribute("classname : ",classname);
            if (isfind)
            {
                isfind = false;
                return "/findoneresult";

            }
            else {
                return "redirect:/findoneStudent";
            }

        }
        return "redirect:/signin";

    }

    //Update Student

    @GetMapping("/updatestudent.html")
    public String GetUpdatePage(Model model){
        SignVerification v = new SignVerification();
        if(v.Verification()){
            model.addAttribute("student",t_student);
            return "/updatestudent";
        }
        return "redirect:/findoneStudent";

    }
    @PostMapping("/updatestudent.html")
    public String Update(Student student){
        studentRepository.save(student);
        return "/ok";
    }

    //delete student
    @GetMapping("deletestudent.html")
    public String Delete(){
        SignVerification v = new SignVerification();
        if(v.Verification()) {
            studentRepository.delete(t_student);
            return "/ok";
        }
        return "redirect:/findoneStudent";
    }

}
