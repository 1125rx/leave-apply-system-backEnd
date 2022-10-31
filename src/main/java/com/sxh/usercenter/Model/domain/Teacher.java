package com.sxh.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 教师信息表
 * @TableName teacher
 */
@TableName(value ="teacher")
@Data
public class Teacher implements Serializable {
    /**
     * 教师账户
     */
    @TableId
    private String teacherAccount;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 教师专业
     */
    private String teacherMajor;

    /**
     * 教师电话
     */
    private String teacherPhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}