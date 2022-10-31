package com.sxh.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxh.usercenter.Model.domain.Email;
import com.sxh.usercenter.service.EmailService;
import com.sxh.usercenter.Mapper.EmailMapper;
import com.sxh.usercenter.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

/**
* @author sxh
* @description 针对表【email】的数据库操作Service实现
* @createDate 2022-09-23 10:23:22
*/
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email>
    implements EmailService{

    private static  final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    private HashMap<String,String> verCodeMap = new HashMap<>();

    @Autowired
    private EmailUtils emailUtils;


    @Override
    public String generateVarCode() {
        //如果是六位，就生成大小为 6 的数组
        char [] numbers = new char[6];
        for (int i=0;i<numbers.length;i++){
            //生成一个在SYMBOLS长度内的随机数，并把SYMBOLS里对应位置的字符拿出来放进numbers数组里
            numbers[i] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        //返会这个个六位随机数
        return new String(numbers);
    }

    @Override
    public void sendEmailVerCode(String emailAddress) {
        String verCode = generateVarCode();
        emailUtils.sendEmail(emailAddress,verCode);
        verCodeMap.put(emailAddress,verCode);
    }

    @Override
    public boolean verifyEmail(String emailAddress, String account, String checkCode) {
        String verCode = verCodeMap.get(emailAddress);
        if (!checkCode.equals(verCode))
            return false;
        else
        {
            verCodeMap.remove(emailAddress);
            Email email = new Email();
            email.setE_account(account);
            email.setE_email(emailAddress);
            return this.save(email);
        }
    }
}




