package com.sxh.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxh.usercenter.Mapper.EmailMapper;
import com.sxh.usercenter.Mapper.UserMapper;
import com.sxh.usercenter.Model.domain.Email;
import com.sxh.usercenter.Model.domain.User;
import com.sxh.usercenter.Model.domain.request.BindEmailRequest;
import com.sxh.usercenter.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.sxh.usercenter.constant.userConstant.USER_LOGIN_STATE;

/**
 * @program: usercenter
 * @description:
 * @author: SXH
 * @create: 2022-09-25 08:29
 **/
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @Resource
    private EmailMapper emailMapper;

    @Resource
    private UserMapper userMapper;

    @PostMapping("/getCode")
    public long getEmailCode(@RequestBody String emailAddress){
        if (StringUtils.isAnyBlank(emailAddress))
            return -2;
        QueryWrapper<Email> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_email",emailAddress);
        if (emailMapper.selectCount(queryWrapper) != 0 )
            return -1;
        emailService.sendEmailVerCode(emailAddress);
        return 1;
    }

    @PostMapping("/verifyCode")
    public boolean verifyEmailCode(@RequestBody BindEmailRequest bindEmailRequest, HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        String account = user.getUserAccount();
        return emailService.verifyEmail(bindEmailRequest.getEmailAddress(),account,bindEmailRequest.getVerCode());
    }
    @GetMapping("/getBinding")
    public boolean getEmailSituation(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        String userAccount = user.getUserAccount();
        QueryWrapper<Email> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_account",userAccount);
        return emailMapper.exists(queryWrapper);
    }

    @GetMapping("/getEmail")
    public String getEmail(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        String userAccount = user.getUserAccount();
        QueryWrapper<Email> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("e_account",userAccount);
        if (emailMapper.exists(queryWrapper))
            return emailMapper.selectOne(queryWrapper).getE_email();
        else
            return null;
    }

    @GetMapping("/unbindemail")
    public boolean unBindingEmail(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        String userAccount = user.getUserAccount();
        int i = emailMapper.deleteById(userAccount);
        return i != 0;
    }
}
