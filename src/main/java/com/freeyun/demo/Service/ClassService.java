package com.freeyun.demo.Service;

import com.freeyun.demo.Domain.StudentClass;
import com.freeyun.demo.Respository.StudentClassRespository;
import com.freeyun.demo.Respository.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClassService {
    @Autowired
    StudentClassRespository studentClassRespository;
    @Autowired
    StudentRespository studentRespository;
    public Page<StudentClass> GetClassList(Integer page)
    {
        Integer size = 10;//Page size
        Sort sort = new Sort(Sort.Direction.ASC,"classno");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<StudentClass> studentClassess = studentClassRespository.findAll(pageable);
        return  studentClassess;
    }
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
    @Transactional
    public  int deleClassInfo(Integer classno)
    {
        StudentClass studentClass;
        try {
            studentClass = studentClassRespository.findById(classno).get();
            studentRespository.deleteByStudentclass(studentClass);
            studentClassRespository.delete(studentClass);
            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
