package com.wangpeng.zhxydemo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.mapper.ClazzMapper;
import com.wangpeng.zhxydemo.pojo.Clazz;
import com.wangpeng.zhxydemo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public IPage<Clazz> getPageOpr(Page<Clazz> page, String name) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
             queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        Page<Clazz> page1 = clazzMapper.selectPage(page, queryWrapper);
        return page1;
    }
}
