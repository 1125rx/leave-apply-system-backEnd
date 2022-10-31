package com.sxh.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 请假次数统计
 * @TableName summary
 */
@TableName(value ="summary")
@Data
public class Summary implements Serializable {
    /**
     * 申请人学号
     */
    @TableId
    private String leaveAccount;

    /**
     * 申请人姓名
     */
    private String leaveName;

    /**
     * 申请人请假次数统计
     */
    private Integer leaveCount;

    /**
     * 申请人请假总天数
     */
    private Integer leaveTotalDays;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}