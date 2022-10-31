package com.sxh.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @program: usercenter
 * @description: 邮箱登录请求体
 * @author: SXH
 * @create: 2022-10-22 10:26
 **/
@Data
public class EmailLoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -4473375786662520496L;

    private String Email;
    private String userPassword;
}
