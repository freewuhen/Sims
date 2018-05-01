package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student,String>{
    Student findDistinctBySname(String sname);
}
