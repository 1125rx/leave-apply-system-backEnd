package com.sxh.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxh.usercenter.Mapper.TeacherMapper;
import com.sxh.usercenter.Mapper.UserMapper;
import com.sxh.usercenter.Model.domain.Apply;
import com.sxh.usercenter.Model.domain.Message;
import com.sxh.usercenter.Model.domain.Teacher;
import com.sxh.usercenter.Model.domain.User;
import com.sxh.usercenter.service.ApplyService;
import com.sxh.usercenter.Mapper.ApplyMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;

import static com.sxh.usercenter.utils.DateUtils.*;

/**
* @author sxh
* @description 针对表【apply】的数据库操作Service实现
* @createDate 2022-07-09 11:53:59
*/
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply>
    implements ApplyService{

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private UserMapper userMapper;



    @Override
    /*
     @Description: 添加新的请假申请
    * @Param: [applyAccount, applyName, applyStart, applyEnd, applyReason]
    * @return: 1：成功提交；0：提交失败；-1：请假日期有误；-2：填入信息有空值；-3：申请人账号不存在；-4：审批人不存在；-5：申请人上次请假状态未消除
    * @Author: SXH
    * @Date: 2022/7/17
    */
    public long setNewApply(String applyAccount, String applyName, Date applyStart, Date applyEnd, String applyReason) {
        if (StringUtils.isAnyBlank(applyAccount,applyName,applyReason))
            return -2;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",applyAccount);
        queryWrapper.eq("userName",applyName);
        User user=userMapper.selectOne(queryWrapper);
        if (user==null)
            return -3;
        int applyClass=user.getUserClass();
        String applyMajor=user.getUserMajor();
        QueryWrapper<Teacher> teacherQueryWrapper=new QueryWrapper<>();
        teacherQueryWrapper.eq("teacherMajor",applyMajor);
        Teacher teacher=teacherMapper.selectOne(teacherQueryWrapper);
        if(teacher==null)
            return -4;
        String applyTeacher=teacher.getTeacherName();
        int applyStatus=0;
        Apply newApply=new Apply();
        newApply.setApplyAccount(applyAccount);
        newApply.setApplyName(applyName);
        newApply.setApplyMajor(applyMajor);
        newApply.setApplyClass(applyClass);
        newApply.setApplyStart(getTimeString(applyStart));
        newApply.setApplyEnd(getTimeString(applyEnd));
        newApply.setApplyDay(getDifferDays(applyEnd,applyStart));
        newApply.setApplyTime(getNowTime());
        newApply.setApplyReason(applyReason);
        newApply.setApplyTeacher(applyTeacher);
        newApply.setApplyStatus(applyStatus);
        newApply.setApplyApprovalTime(null);
        boolean save = this.save(newApply);
        if (!save)
            return 0;
        return 1;
    }

    @Override
    /**
    * @Description: 审批请假申请
    * @Param: [applyAccount, applyStatus]
    * @return: 审批是否成功
    * @Author: SXH
    * @Date: 2022/7/23
    */
    public boolean setApplyStatus(int applyId, int applyStatus){
        UpdateWrapper<Apply> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("applyId",applyId).set("applyStatus",applyStatus);
        updateWrapper.eq("applyId",applyId).set("applyApprovalTime",getNowTime());
        return this.update(null, updateWrapper);
    }

    @Override
    public Message getMessage(String applyMajor) {
        Long waitPass;
        Long alreadyPass;
        Long noPass;
        Long hasPassed;
        Long allMessage;
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("applyStatus",0).eq("applyMajor",applyMajor);
        waitPass=applyMapper.selectCount(queryWrapper);
        QueryWrapper<Apply> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("applyStatus",1).eq("applyMajor",applyMajor);
        alreadyPass=applyMapper.selectCount(queryWrapper2);
        QueryWrapper<Apply> queryWrapper3=new QueryWrapper<>();
        queryWrapper3.eq("applyStatus",2).eq("applyMajor",applyMajor);
        noPass=applyMapper.selectCount(queryWrapper3);
        QueryWrapper<Apply> queryWrapper4=new QueryWrapper<>();
        queryWrapper4.eq("applyStatus",3).eq("applyMajor",applyMajor);
        hasPassed=applyMapper.selectCount(queryWrapper4);
        allMessage=waitPass+alreadyPass+noPass+hasPassed;
        Message message=new Message();
        message.setWaitPass(waitPass);
        message.setAlreadyPass(alreadyPass);
        message.setNoPass(noPass);
        message.setHasPassed(hasPassed);
        message.setAllMessage(allMessage);
        return message;

    }

    @Override
    /**
    * @Description: 获取请假申请信息
    * @Param: [applyAccount]
    * @return: apply
    * @Author: SXH
    * @Date: 2022/8/24
    */
    public Apply getUserApply(String applyAccount) {
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("applyAccount",applyAccount).le("applyStatus",1);
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("userAccount",applyAccount);
        User user=userMapper.selectOne(userQueryWrapper);
        if(applyMapper.selectCount(queryWrapper)==0){
            Apply apply=new Apply();
            apply.setApplyAccount(user.getUserAccount());
            apply.setApplyName(user.getUserName());
            apply.setApplyMajor(user.getUserMajor());
            apply.setApplyClass(user.getUserClass());
            apply.setApplyStart(" ");
            apply.setApplyEnd(" ");
            apply.setApplyDay(0);
            apply.setApplyTime(" ");
            apply.setApplyReason(" ");
            apply.setApplyTeacher(this.getTeacherName(applyAccount));
            apply.setApplyStatus(-1);
            apply.setApplyApprovalTime(" ");

            return apply;
        }
        else
            return applyMapper.selectOne(queryWrapper);
    }

    @Override
    public String getTeacherName(String applyAccount) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("userAccount",applyAccount);
        User user=userMapper.selectOne(userQueryWrapper);
        QueryWrapper<Teacher> teacherQueryWrapper=new QueryWrapper<>();
        teacherQueryWrapper.eq("teacherMajor",user.getUserMajor());
        Teacher teacher=teacherMapper.selectOne(teacherQueryWrapper);
        return teacher.getTeacherName();
    }
}




