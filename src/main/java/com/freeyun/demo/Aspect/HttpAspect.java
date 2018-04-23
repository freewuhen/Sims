package com.freeyun.demo.Aspect;

import com.freeyun.demo.Controller.VerificationController;
import com.freeyun.demo.Verification.SignVerification;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpAspect {
    private final  static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Before("execution(public * com.freeyun.demo.Controller.ScoreController.*(..))")
    public void verification() {
        logger.info("111111111111111111111111111111111111111111111111111111");
        SignVerification v = new SignVerification();
        if(v.Verification() == false){
            VerificationController verificationController = new VerificationController();
            verificationController.returnSignPage();
            return;
        }
        return;
    }
}
