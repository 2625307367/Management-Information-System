package com.wangpeng.zhxydemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangpeng.zhxydemo.Utils.MD5;
import com.wangpeng.zhxydemo.mapper.AdminMapper;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Clazz;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.loginForm;
import com.wangpeng.zhxydemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(loginForm loginForm) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
//        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()) );
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(Long userId) {
      return   adminMapper.selectById(userId);
    }

    @Override
    public IPage<Admin> getPageOri(Page<Admin> page, String adminName) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(adminName)) {
            queryWrapper.like("name",adminName);
        }
        queryWrapper.orderByDesc("id");
        Page<Admin> page1 = adminMapper.selectPage(page, queryWrapper);
        return page1;
    }

    @Override
    public Admin updateBypassword(int intValue, String oldPassword) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id",intValue);
        queryWrapper.like("password",MD5.encrypt(oldPassword));
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;

    }
}


