package com.freeyun.demo.Verification;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignVerification {// 登录验证
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session= request.getSession();
        public boolean Verification(){
            if (session.getAttribute("Admin") == null)
            {
                return false;
            }
            return true;
        }

}
