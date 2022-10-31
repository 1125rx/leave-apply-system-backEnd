package com.sxh.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @program: usercenter
 * @description:
 * @author: SXH
 * @create: 2022-09-26 18:42
 **/
@Data
public class BindEmailRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -1912539662321722341L;

    private String emailAddress;
    private String verCode;
}
