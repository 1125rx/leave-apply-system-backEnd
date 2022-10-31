package com.sxh.usercenter.service;

import com.sxh.usercenter.Model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author sxh
* @description 针对表【user(用户数据)】的数据库操作Service
* @createDate 2022-07-04 23:32:31
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount,String userPassword,String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafetyUser(User user);

    String getEncryptPassword(String orginPassword);

    long setNewUser(String userAccount,String userName,String gender,String userMajor,int userClass);

    int userLogout(HttpServletRequest request);

    boolean userDelete(String userAccount);

    long changeUserPassword(String userAccount, String oldPassword, String newPassword, String checkPassword);

    boolean resetUserPassword(String userAccount);

    long updateUser(String userAccount,String userName,String gender,String userMajor,int userClass);
}
