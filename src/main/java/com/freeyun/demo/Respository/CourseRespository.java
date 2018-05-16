package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Course;
import com.freeyun.demo.Domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRespository extends JpaRepository<Course,String> {
    Course findDistinctByCname(String cname);
    void deleteByTeacher(Teacher teacher);
}
