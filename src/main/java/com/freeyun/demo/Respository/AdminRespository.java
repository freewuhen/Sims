package com.freeyun.demo.Respository;
import com.freeyun.demo.Domain.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<Admin,String>{

}