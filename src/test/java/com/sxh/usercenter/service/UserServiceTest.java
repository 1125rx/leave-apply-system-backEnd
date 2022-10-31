package com.sxh.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxh.usercenter.Mapper.UserMapper;
import com.sxh.usercenter.Model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;


    @Test
    public void testRegister(){
        System.out.println(userService.userRegister("202024100623","11111111","11111111"));
    }

    @Test
    public void testAddUser(){
        User user=new User();
        user.setUserId(1);
        user.setUserAccount("111");
        user.setUserPassword("111");
        user.setAvatarUrl("111");
        user.setGender("111");
        user.setUserRole(0);
        user.setUserStatus(0);

        boolean result = userService.save(user);
        System.out.println(user.getUserId());
        Assertions.assertTrue(result);


    }
    @Test
    public void getUser(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        List<User> list=userService.list(queryWrapper);
        System.out.println(list);

    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "dogyupi";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void createUser(){
        String userAccount="202024100624";
        String userName="JISOO";
        String gender="女";
        String userMajor="计算机";
        int userClass=4;
        long result = userService.setNewUser(userAccount, userName, gender, userMajor, userClass);
        Assertions.assertTrue(result>0);
        System.out.println(result);
    }

    @Test
    void deleteUser(){
        String userAccount="202024106987";
        System.out.println(userService.userDelete(userAccount));
    }

    @Test
    void deleteTest(){
        String userAccount = "154945646494";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        System.out.println(userMapper.delete(queryWrapper));
    }
}