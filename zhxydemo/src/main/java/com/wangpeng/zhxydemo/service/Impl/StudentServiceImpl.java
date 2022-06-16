package com.wangpeng.zhxydemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangpeng.zhxydemo.Utils.MD5;
import com.wangpeng.zhxydemo.mapper.StudentMapper;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Student;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.pojo.loginForm;
import com.wangpeng.zhxydemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("studentServiceImpl")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
   private StudentMapper studentMapper;


    @Override
    public Student getStudentById(Long userId) {
        return studentMapper.selectById(userId);
    }

    @Override
    public Student login(loginForm loginForm) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = studentMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public IPage<Student> getPageOri(Page<Student> page, String name) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        Page<Student> studentPager = studentMapper.selectPage(page, queryWrapper);
        return  studentPager;
    }

    @Override
    public Student updateBypassword(int intValue, String oldPassword) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",intValue);
        queryWrapper.eq("password",MD5.encrypt(oldPassword));
        Student student = studentMapper.selectOne(queryWrapper);
        return student;
    }
}
