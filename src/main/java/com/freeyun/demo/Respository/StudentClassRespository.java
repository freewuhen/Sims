package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRespository extends JpaRepository<StudentClass,Integer> {

}
