package com.wangpeng.zhxydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangpeng.zhxydemo.pojo.Student;
import com.wangpeng.zhxydemo.pojo.loginForm;

public interface StudentService extends IService<Student> {


    Student getStudentById(Long userId);


    Student login(loginForm loginForm);

    IPage<Student> getPageOri(Page<Student> page, String name);

    Student updateBypassword(int intValue, String oldPassword);
}
