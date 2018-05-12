package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.StudentClassRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ClassService {
    @Autowired
    StudentClassRespository studentClassRespository;
    @GetMapping(value = "/getClassInfo")
    public Object GetClassList(@RequestParam Integer page)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"sno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<StudentClass> studentClassess = studentClassRespository.findAll(pageable);
        return  studentClassess;
    }
    @PostMapping("/addClassInfo")
    public int addInfo(StudentClass studentClass)
    {
        StudentClass cls = new StudentClass();
        cls = studentClassRespository.findDistinctByClassname(studentClass.getClassname());
        if(cls == null)//classname existed
        {
          studentClassRespository.save(studentClass);
          return 1;
        }
        else{
            return 0;
        }
    }
    @PostMapping("/deleClassInfo")
    public  int deleClassInfo(@RequestParam Integer classno)
    {
        StudentClass studentClass = studentClassRespository.findById(classno).get();
        studentClassRespository.delete(studentClass);
        return 1;
    }


}
