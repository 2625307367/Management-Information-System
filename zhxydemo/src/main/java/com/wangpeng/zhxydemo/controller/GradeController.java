package com.wangpeng.zhxydemo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.service.GradeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制")
@RestController
@RequestMapping("sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;
   // getGrades/1/3?gradeName
    @GetMapping("/getGrades/{pageNo}/{pageNum}")
    public Result getGrades(
         @PathVariable   Integer pageNo,
         @PathVariable   Integer pageNum,
         String gradeName
    ) {
        Page<Grade> page = new Page<>(pageNo, pageNum);
        IPage<Grade> iPage = gradeService.getPageOri(page, gradeName);
        return Result.ok(iPage);

    }
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade)
    {
        boolean result = gradeService.saveOrUpdate(grade);
      return   Result.ok(result);
    }
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@RequestBody  List<Integer> ids)
    {
        boolean result = gradeService.removeByIds(ids);
        return Result.ok(result);
    }
    @GetMapping("/getGrades")
    public Result getGrades()
    {
        List<Grade> list = gradeService.list();
        return Result.ok(list);
    }

}
