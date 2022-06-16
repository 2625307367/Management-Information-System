package com.wangpeng.zhxydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.wangpeng.zhxydemo.pojo.Clazz;

public interface ClazzService extends IService<Clazz> {

    IPage<Clazz> getPageOpr(Page<Clazz> page, String name);
}
