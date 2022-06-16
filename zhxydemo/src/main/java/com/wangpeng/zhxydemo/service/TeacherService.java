package com.wangpeng.zhxydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.pojo.loginForm;

public interface TeacherService extends IService<Teacher> {

    Teacher login(loginForm loginForm);
    Teacher getTeacherById(Long userId);

    IPage<Teacher> getPageOri(Page<Teacher> page, String name);

    Teacher updateBypassword(int intValue, String oldPassword);
}
