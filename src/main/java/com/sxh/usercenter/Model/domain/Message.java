package com.sxh.usercenter.Model.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: usercenter
 * @description:
 * @author: SXH
 * @create: 2022-07-27 21:31
 **/

@Data
public class Message implements Serializable {
    private Long waitPass;
    private Long alreadyPass;
    private Long noPass;
    private Long hasPassed;
    private Long allMessage;
}
