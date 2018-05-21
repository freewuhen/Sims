package com.freeyun.demo.Service;

import com.freeyun.demo.Domain.Admin;
import com.freeyun.demo.Respository.AdminRespository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminService {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRespository adminRespository;

    public int signin(Admin admin,HttpSession session){
        Admin findadmin = null;
        try {
            findadmin = adminRespository.findById(admin.getUsername()).get();
            if(findadmin.getPassword().equals( admin.getPassword())){//password is right too

                session.setAttribute("Admin",admin.getUsername());
                return 1;// sign in success
            }
            else{
                return -1;// password is error
            }

        }catch (Exception e) {
            if (findadmin == null)
                return 0;
            else
                return -2;// unknown exception
        }


    }
    public int signup(Admin admin){
        Admin findadmin = null;
        int username_length,password_length;
        try {
            findadmin = adminRespository.findById(admin.getUsername()).get();
            return 0; // username is exist
        }catch (Exception e) {
            logger.error("findadmin"+findadmin);
            if (findadmin == null)
            {
                username_length = admin.getUsername().length();
                password_length = admin.getUsername().length();
                if(username_length > 6 && username_length < 20 )
                {
                    if (password_length > 6 && password_length < 20)
                    {
                        adminRespository.save(admin);
                        return 1;
                    }
                    else
                        return -2;// password is too long
                }
                else
                    return -1;// username is too long
            }
            return -3;// unknown exception
        }



    }
}
