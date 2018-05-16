package com.freeyun.demo.Respository;

import com.freeyun.demo.Domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRespository extends JpaRepository<Teacher,String> {
  List<Teacher> findByTname(String tname);
}
