package com.wangpeng.zhxydemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/teacherController")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    //getTeachers/1/3?name
    @GetMapping("/getTeachers/{pageNo}/{pageNum}")
    public Result getTeachers(
            @PathVariable   Integer pageNo,
            @PathVariable   Integer pageNum,
            String name
    ) {
        Page<Teacher> page = new Page<>(pageNo, pageNum);
        IPage<Teacher> iPage = teacherService.getPageOri(page, name);
        return Result.ok(iPage);
    }
    @PostMapping("/saveOrUpdateTeacher")
    public  Result saveOrUpdateTeacher(@RequestBody Teacher teacher)
    {
        boolean resule = teacherService.saveOrUpdate(teacher);
        return  Result.ok(resule);
    }
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids)
    {
        boolean result = teacherService.removeByIds(ids);
        return  Result.ok(result);

    }


}
