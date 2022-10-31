package com.sxh.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxh.usercenter.Mapper.ApplyMapper;
import com.sxh.usercenter.Model.domain.Apply;
import com.sxh.usercenter.Model.domain.Message;
import com.sxh.usercenter.Model.domain.User;
import com.sxh.usercenter.Model.domain.request.SetApplyStatusRequest;
import com.sxh.usercenter.Model.domain.request.SetNewApplyRequest;
import com.sxh.usercenter.service.ApplyService;
import com.sxh.usercenter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sxh.usercenter.constant.userConstant.USER_LOGIN_STATE;

/**
 * @program: usercenter
 * @description: 请假申请实体类
 * @author: SXH
 * @create: 2022-07-27 21:46
 **/

@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;
    @Resource
    private UserService userService;
    @Resource
    private ApplyMapper applyMapper;

    @GetMapping("/getMessage")
    /** 
    * @Description: 获取请假申请信息 
    * @Param: [request] 
    * @return:
    * @Author: SXH 
    * @Date: 2022/7/28 
    */ 
    public Message getMessage(HttpServletRequest request){
        if (!isTeacher(request))
            return null;
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User)userObj;
        String major = user.getUserMajor();
        Message showMessage=new Message();
        showMessage=applyService.getMessage(major);
        return showMessage;
    }

    public boolean isTeacher(HttpServletRequest request){
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User)userObj;
        return user != null && user.getUserRole() != 1;
    }

    @PostMapping("/addApply")
    /** 
    * @Description: 添加新的请假申请 
    * @Param: [applyRequest, request] 
    * @return:  
    * @Author: SXH 
    * @Date: 2022/7/28 
    */ 
    public Long addNewApply(@RequestBody SetNewApplyRequest applyRequest,HttpServletRequest request){
        if (applyRequest==null)
            return null;
        Date startTime=applyRequest.getApplyStart();
        Date endTime=applyRequest.getApplyEnd();
        String applyReason=applyRequest.getApplyReason();

        Object object=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) object;
        if (user==null)
            return null;
        String applyAccount=user.getUserAccount();
        String applyName=user.getUserName();
        return applyService.setNewApply(applyAccount,applyName,startTime,endTime,applyReason);

    }

    @GetMapping("/getApply")
    /**
    * @Description:
    * @Param:
    * @return:
    * @Author: SXH
    * @Date: 2022/8/4
    */
    public List<Apply> getAllApply(HttpServletRequest request){
        if (!isTeacher(request))
            return new ArrayList<>();
        Object obj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) obj;
        String applyTeacher = user.getUserName();
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        return applyService.list(queryWrapper.eq("applyTeacher",applyTeacher));
    }

    @GetMapping("/getApplyHistory")
    /**
    * @Description: 获取当前用户的请假记录
    * @Param: [request]
    * @return:
    * @Author: SXH
    * @Date: 2022/8/28
    */
    public List<Apply> getUserApplyHistory(HttpServletRequest request){
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        Object obj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) obj;
        String applyAccount = user.getUserAccount();
        queryWrapper.eq("applyAccount",applyAccount);
        return applyService.list(queryWrapper);
    }

    @GetMapping("/getCurrentApplyStatus")
    /**
    * @Description: 获取当前的请假状态
    * @Param: [request]
    * @return:
    * @Author: SXH
    * @Date: 2022/9/16
    */
    public boolean getUserCurrentApplyStatus(HttpServletRequest request){
        QueryWrapper<Apply> queryWrapper=new QueryWrapper<>();
        Object obj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) obj;
        String applyAccount = user.getUserAccount();
        queryWrapper.eq("applyAccount",applyAccount);
        queryWrapper.le("applyStatus",1);
        return applyService.count(queryWrapper) <= 0;
    }

    @GetMapping("/getUserApply")
    /**
    * @Description: 获取当前用户的请假申请
    * @Param: [request]
    * @return: Apply
    * @Author: SXH
    * @Date: 2022/8/23
    */
    public Apply getUserApply(HttpServletRequest request){
        Object obj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) obj;
        String applyAccount = user.getUserAccount();
        return applyService.getUserApply(applyAccount);
    }

    @PostMapping("/setApplyStatusSuccess")
    /**
    * @Description: 审批请假申请通过
    * @Param: [applyAccount, setApplyNewStatus]
    * @return:
    * @Author: SXH
    * @Date: 2022/8/30
    */
    public boolean setApplyStatusSuccess(@RequestBody  String applyAccount){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("applyAccount",applyAccount).eq("applyStatus",0);
        Apply apply = applyMapper.selectOne(queryWrapper);
        return applyService.setApplyStatus(apply.getApplyId(),1);
    }

    @PostMapping("/setApplyStatusError")
    /**
     * @Description: 审批请假申请不通过
     * @Param: [applyAccount, setApplyNewStatus]
     * @return:
     * @Author: SXH
     * @Date: 2022/8/30
     */
    public boolean setApplyStatusError(@RequestBody  String applyAccount){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("applyAccount",applyAccount).eq("applyStatus",0);
        Apply apply = applyMapper.selectOne(queryWrapper);
        return applyService.setApplyStatus(apply.getApplyId(),2);
    }

    @PostMapping("/setApplyStatusPassed")
    /**
    * @Description: 销假
    * @Param: [applyAccount]
    * @return:
    * @Author: SXH
    * @Date: 2022/9/26
    */
    public boolean setApplyStatusPassed(@RequestBody  String applyAccount){
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("applyAccount",applyAccount).eq("applyStatus",1);
        Apply apply = applyMapper.selectOne(queryWrapper);
        return applyService.setApplyStatus(apply.getApplyId(),3);
    }

    @GetMapping("/getNewCount")
    /**
    * @Description: 获取待处理的申请的数目
    * @Param: [request]
    * @return:
    * @Author: SXH
    * @Date: 2022/9/26
    */
    public long getNewApplyCount(HttpServletRequest request){
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User)userObj;
        String applyTeacher = user.getUserName();
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("applyTeacher",applyTeacher).eq("applyStatus",0);
        return applyMapper.selectCount(queryWrapper);
    }
}
