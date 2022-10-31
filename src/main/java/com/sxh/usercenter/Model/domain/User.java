package com.sxh.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户数据
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 账户
     */
    private String userAccount;

    /**
     * 学生姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 性别
     */
    private String gender;

    /**
     * 专业
     */
    private String userMajor;

    /**
     * 学生班级
     */
    private Integer userClass;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 用户身份，1为学生，2为老师，默认为学生
     */
    private Integer userRole;

    /**
     * 用户账户状态，0为正常，1为异常，默认为0
     */
    private Integer userStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}