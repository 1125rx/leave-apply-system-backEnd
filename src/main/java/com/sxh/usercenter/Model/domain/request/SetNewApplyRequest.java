package com.sxh.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: usercenter
 * @description: 添加新的请假申请请求体
 * @author: SXH
 * @create: 2022-07-28 01:01
 **/
@Data
public class SetNewApplyRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3689529303701810969L;

    private Date applyStart;
    private Date applyEnd;
    private String applyReason;

}
