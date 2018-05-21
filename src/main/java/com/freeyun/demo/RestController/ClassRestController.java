package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.ScoreRespository;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import com.freeyun.demo.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class ClassRestController {
    @Autowired
    ClassService classService;
    @GetMapping(value = "/getClassInfo")
    public Object GetClassList(@RequestParam Integer page)
    {
        Page<StudentClass> classes = classService.GetClassList(page);
        return classes;
    }
    @PostMapping("/addClassInfo")
    public int addInfo(StudentClass studentClass)
    {
        int status = classService.addInfo(studentClass);
        return status;
    }
    @PostMapping("/deleClassInfo")
    @Transactional
    public  int deleClassInfo(@RequestParam Integer classno)
    {
        int status = classService.deleClassInfo(classno);
        return status;
    }


}
