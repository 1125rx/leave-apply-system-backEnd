package com.sxh.usercenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxh.usercenter.Model.domain.Apply;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;

import static com.sxh.usercenter.utils.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApplyServiceTest {

    @Resource
    private ApplyService applyService;



    @Test
    void testCompare(){
        Date date1=new Date();
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("applyAccount","202024100622");
        Apply apply=applyService.getOne(queryWrapper);
        Date date2=getTimeDate(apply.getApplyStart());
        System.out.println(date2.compareTo(date1));
    }

    @Test
    void testUserApply(){
        System.out.println(applyService.getUserApply("202024100622"));
    }

}