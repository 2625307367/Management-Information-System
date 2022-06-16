package com.wangpeng.zhxydemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.pojo.Clazz;
import com.wangpeng.zhxydemo.service.ClazzService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/clazzController")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    //getClazzsByOpr/1/3?name=%E4%B8%89
    @GetMapping("getClazzsByOpr/{pageNo}/{pageNum}")
    public Result getClazzsByOpr(
          @PathVariable  Integer pageNo,
          @PathVariable  Integer pageNum,
            String name
    )
    {
        Page<Clazz> page = new Page<>(pageNo, pageNum);
         IPage<Clazz> iPage= clazzService.getPageOpr(page,name);
         return Result.ok(iPage);
    }
    @GetMapping("/getClazzs")
    public  Result getClazzs()
    {
        List<Clazz> list = clazzService.list();
        return Result.ok(list);
    }

}
