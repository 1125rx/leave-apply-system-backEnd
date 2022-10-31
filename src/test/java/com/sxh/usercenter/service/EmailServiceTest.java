package com.sxh.usercenter.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceTest {
    @Resource
    private EmailService emailService;

    @Test
    void testEmailSend(){
        emailService.sendEmailVerCode("1770986733@qq.com");
        String code = "  ";
        System.out.println(emailService.verifyEmail("1770986733@qq.com","202024100620",code));
    }

}