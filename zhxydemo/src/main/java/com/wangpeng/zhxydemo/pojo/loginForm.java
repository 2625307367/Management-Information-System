package com.wangpeng.zhxydemo.pojo;

import lombok.Data;

@Data
public class loginForm {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
