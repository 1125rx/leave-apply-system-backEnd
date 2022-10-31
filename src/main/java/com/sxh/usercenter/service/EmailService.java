package com.sxh.usercenter.service;

import com.sxh.usercenter.Model.domain.Email;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sxh
* @description 针对表【email】的数据库操作Service
* @createDate 2022-09-23 10:23:22
*/
public interface EmailService extends IService<Email> {

    String generateVarCode();

    void sendEmailVerCode(String emailAddress);

    boolean verifyEmail(String emailAddress,String account,String checkCode);

}
