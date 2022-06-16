package com.wangpeng.zhxydemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangpeng.zhxydemo.pojo.Grade;


public interface GradeService extends IService<Grade> {

    IPage<Grade> getPageOri(Page<Grade> page, String gradeName);
}
