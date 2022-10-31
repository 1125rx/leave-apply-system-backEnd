package com.sxh.usercenter.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.sxh.usercenter.utils.DateUtils.getNowDate;

/**
 * @program: usercenter
 * @description: 发送邮箱验证码工具类
 * @author: SXH
 * @create: 2022-09-23 10:28
 **/
@Service
public class EmailUtils {

    @Resource
    private JavaMailSenderImpl mailSender;

    public void sendEmail(String toEmail,String verCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("songxh_1125rx@163.com");
        message.setTo(toEmail);
        message.setSubject("绑定邮箱");
        message.setText("当前时间为："+getNowDate()+"\n验证码为："+verCode+"\n(这是一封通过自动发送的邮件，请不要直接回复）");
        mailSender.send(message);
    }
}
