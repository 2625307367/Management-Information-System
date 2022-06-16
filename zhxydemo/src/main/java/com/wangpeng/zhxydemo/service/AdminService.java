package com.wangpeng.zhxydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.loginForm;


public interface AdminService extends IService<Admin> {


    Admin login(loginForm loginForm);

    Admin getAdminById(Long userId);

    IPage<Admin> getPageOri(Page<Admin> page, String adminName);


    Admin updateBypassword(int intValue, String oldPassword);
}
