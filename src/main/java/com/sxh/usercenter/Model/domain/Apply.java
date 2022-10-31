package com.sxh.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName apply
 */
@TableName(value ="apply")
@Data
public class Apply implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer applyId;

    /**
     * 请假人学号
     */
    private String applyAccount;

    /**
     * 请假人姓名
     */
    private String applyName;

    /**
     * 请假人专业
     */
    private String applyMajor;

    /**
     * 请假人班级
     */
    private Integer applyClass;

    /**
     * 请假开始时间
     */
    private String applyStart;

    /**
     * 请假结束时间
     */
    private String applyEnd;

    /**
     * 请假时长
     */
    private Integer applyDay;

    /**
     * 申请时间
     */
    private String applyTime;

    /**
     * 请假申请原因
     */
    private String applyReason;

    /**
     * 审批老师
     */
    private String applyTeacher;

    /**
     * 请假状态：0-未审批 1-通过审批 2-未通过审批 3-已销假
     */
    private Integer applyStatus;

    /**
     * 审批时间
     */
    private String applyApprovalTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}