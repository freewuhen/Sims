package com.freeyun.demo.Controller;

import com.freeyun.demo.Domain.Student;
import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Domain.Student_info;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;

@Controller
public class ManageStudentController {//管理学生信息
    @Autowired
    private StudentRespository studentRepository;
    @Autowired private StudentClassRespository classRespository;
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
    @GetMapping(value = {"/findoneresult","/findoneresult.html"})
    public String FindoneResult(Model model){
        SignVerification v = new SignVerification();
        StudentClass sclass;
        String classname;
        if(v.Verification())
        {
            System.out.print("Verification pass");
            try {
                Student student = studentRepository.findById(id).get();
                sclass = student.getStudentclass();
                classname = sclass.getClassname();
                t_student = student;
                model.addAttribute("student",student);
                model.addAttribute("classname",classname);
                if (isfind)
                {
                    isfind = false;
                    return "/findoneresult";

                }
                else {
                    return "redirect:/findoneStudent";
                }
            }catch (NoSuchElementException e)
            {
                return "/find_error";
            }




        }
        return "redirect:/signin";

    }

    //Update Student

    @GetMapping("/updatestudent.html")
    public String GetUpdatePage(Model model){
        SignVerification v = new SignVerification();
        if(v.Verification()){
            //get student class info
            StudentClass sclass = t_student.getStudentclass();
            String classname = sclass.getClassname();

            Student_info student_info = new Student_info();
            student_info.setSno(t_student.getSno());
            student_info.setSname(t_student.getSname());
            student_info.setSage(t_student.getSage());
            student_info.setSsex(t_student.getSsex());
            student_info.setClassname(classname);



            model.addAttribute("student",student_info);
            return "/updatestudent";
        }
        return "redirect:/findoneStudent";

    }
    @PostMapping("/updatestudent.html")
    public String Update(Student_info student_info){

        Student student = new Student();
        student.setSno(student_info.getSno());
        student.setSname(student_info.getSname());
        student.setSage(student_info.getSage());
        student.setSsex(student_info.getSsex());

        StudentClass cls = classRespository.findDistinctByClassname(student_info.getClassname());
        if (cls == null) // classnmae is not exist
        {
            return "/error";
        }
        student.setStudentclass(cls);
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
