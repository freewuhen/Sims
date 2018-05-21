package com.freeyun.demo.Service;

import com.freeyun.demo.Domain.StudentClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassServiceTest {
    @Autowired private ClassService classService;
    private final Logger logger = LoggerFactory.getLogger(ClassService.class);
    @Test
    public void getClassList() {
        Integer page = 0;
        Integer sise = 10;
        Page<StudentClass> classes= classService.GetClassList(page);
        assertThat(classes.getSize(),equalTo(sise));
    }
    @Test
    public void addInfo()
    {
        StudentClass studentClass = new StudentClass();
        studentClass.setClassname("170307");
        int status = classService.addInfo(studentClass);
        assertThat(status,equalTo(1));
        status = classService.addInfo(studentClass);
        assertThat(status,equalTo(0));

    }
}