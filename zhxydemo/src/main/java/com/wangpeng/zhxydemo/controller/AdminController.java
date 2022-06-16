package com.wangpeng.zhxydemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //getAllAdmin/1/3?adminName=%E7%9A%84
    @GetMapping("/getAllAdmin//{pageNo}/{pageNum}")
    public Result getGrades(
            @PathVariable   Integer pageNo,
            @PathVariable   Integer pageNum,
            String adminName
    ) {
        Page<Admin> page = new Page<>(pageNo, pageNum);
        IPage<Admin> iPage = adminService.getPageOri(page, adminName);
        return Result.ok(iPage);

    }
    @PostMapping("/saveOrUpdateAdmin")
    public  Result saveOrUpdateTeacher(@RequestBody Admin admin)
    {
        boolean resule = adminService.saveOrUpdate(admin);
        return  Result.ok(resule);
    }
    @DeleteMapping("/deleteAdmin")
    public Result deleteTeacher(@RequestBody List<Integer> ids)
    {
        boolean result = adminService.removeByIds(ids);
        return  Result.ok(result);

    }

}
