package com.sxh.usercenter.service;

import com.sxh.usercenter.Model.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

    @Resource
    private TeacherService teacherService;

    @Test
    void addTest(){
        Teacher teacher=new Teacher();
        teacher.setTeacherAccount("333");
        teacher.setTeacherName("111");
        boolean save = teacherService.save(teacher);
        teacher.setTeacherMajor("111");
        System.out.println(save);


    }

}