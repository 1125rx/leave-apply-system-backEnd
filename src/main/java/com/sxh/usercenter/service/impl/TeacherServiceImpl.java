package com.sxh.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxh.usercenter.Model.domain.Teacher;
import com.sxh.usercenter.service.TeacherService;
import com.sxh.usercenter.Mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author sxh
* @description 针对表【teacher(教师信息表)】的数据库操作Service实现
* @createDate 2022-09-21 14:33:34
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




