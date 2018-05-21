package com.freeyun.demo.RestController;

import com.freeyun.demo.Domain.Teacher;
import com.freeyun.demo.Respository.CourseRespository;
import com.freeyun.demo.Respository.TeacherRespository;
import com.freeyun.demo.Service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TeacherRestContrller {
    @Autowired
    private TeacherService teacherService;
    private final static Logger logger = LoggerFactory.getLogger(TeacherRestContrller.class);
    @GetMapping("/getTeacherList")
    public Object getTeacherList(@RequestParam Integer page)
    {
        Page<Teacher> teachers = teacherService.getTeacherList(page);
        return teachers;
    }
    @PostMapping("/addTeacher")
    public int addTeacher(Teacher teacher)
    {
      int status = teacherService.addTeacher(teacher);
      return status;
    }
    @PostMapping("/delteTeacher")
    public int delteTeacher(@RequestParam String tno)
    {
        int status = teacherService.delteTeacher(tno);
        return status;
    }
    @PostMapping("/updateTeacher")
    public int updateTeacher(Teacher teacher)
    {
        int status = teacherService.updateTeacher(teacher);
        return status;

    }

}
