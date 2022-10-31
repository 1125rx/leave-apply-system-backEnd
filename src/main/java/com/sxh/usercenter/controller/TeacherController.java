package com.sxh.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxh.usercenter.Model.domain.Teacher;
import com.sxh.usercenter.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: usercenter
 * @description:
 * @author: SXH
 * @create: 2022-09-25 11:29
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @Resource
    private TeacherService teacherService;

    @GetMapping("/showTea")
    public List<Teacher> showTeachers(String teacherName){
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(teacherName))
            queryWrapper.eq("teacherName",teacherName);
        return teacherService.list(queryWrapper);
    }
}
