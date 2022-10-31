package com.sxh.usercenter.Model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName email
 */
@TableName(value ="email")
@Data
public class Email implements Serializable {
    /**
     * 
     */
    @TableId
    private String e_account;

    /**
     * 
     */
    private String e_email;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}