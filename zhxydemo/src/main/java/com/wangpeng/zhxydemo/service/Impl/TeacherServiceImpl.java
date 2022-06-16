package com.wangpeng.zhxydemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangpeng.zhxydemo.Utils.MD5;
import com.wangpeng.zhxydemo.mapper.TeacherMapper;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.pojo.loginForm;
import com.wangpeng.zhxydemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher login(loginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(Long userId) {
        return  teacherMapper.selectById(userId);
    }

    @Override
    public IPage<Teacher> getPageOri(Page<Teacher> page, String name) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        Page<Teacher> teacherPage = teacherMapper.selectPage(page, queryWrapper);
        return  teacherPage;
    }

    @Override
    public Teacher updateBypassword(int intValue, String oldPassword) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",intValue);
        queryWrapper.eq("password",oldPassword);
        Teacher teacher = teacherMapper.selectOne(queryWrapper);
        return teacher;
    }


}
