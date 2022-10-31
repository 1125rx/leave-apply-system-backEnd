package com.sxh.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @program: usercenter
 * @description:
 * @author: SXH
 * @create: 2022-09-25 10:52
 **/
@Data
public class SetApplyStatusRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3906860900460938347L;

    private String applyAccount;
    private int applyId;
}

