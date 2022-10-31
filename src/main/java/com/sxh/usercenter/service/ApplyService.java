package com.sxh.usercenter.service;

import com.sxh.usercenter.Model.domain.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sxh.usercenter.Model.domain.Message;

import java.util.Date;

/**
* @author sxh
* @description 针对表【apply】的数据库操作Service
* @createDate 2022-07-09 11:53:59
*/
public interface ApplyService extends IService<Apply> {

    long setNewApply(String applyAccount, String applyName, Date applyStart, Date applyEnd, String applyReason);

    /**
    * @Description: 审批请假申请
    * @Param: [applyAccount, applyStatus]
    * @return: 审批是否成功
    * @Author: SXH
    * @Date: 2022/7/23
    */
    boolean setApplyStatus(int applyId, int applyStatus);

    Message getMessage(String applyMajor);

    Apply getUserApply(String applyAccount);

    String getTeacherName(String applyAccount);

}
