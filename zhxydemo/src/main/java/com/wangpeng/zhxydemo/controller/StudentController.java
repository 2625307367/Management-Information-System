package com.wangpeng.zhxydemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangpeng.zhxydemo.Utils.Result;
import com.wangpeng.zhxydemo.pojo.Grade;
import com.wangpeng.zhxydemo.pojo.Student;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;
  //getStudentByOpr/1/3?name
  @GetMapping("/getStudentByOpr/{pageNo}/{pageNum}")
  public Result getStudent(
          @PathVariable   Integer pageNo,
          @PathVariable   Integer pageNum,
          String name
  ) {
      Page<Student> page = new Page<>(pageNo, pageNum);
      IPage<Student> iPage = studentService.getPageOri(page, name);
      return Result.ok(iPage);
  }
    @PostMapping("/addOrUpdateStudent")
    public Result saveOrUpdateGrade(@RequestBody Student student)
    {
        boolean result = studentService.saveOrUpdate(student);
        return   Result.ok(result);
    }
    @DeleteMapping("/delStudentById")
    public Result deleteTeacher(@RequestBody List<Integer> ids)
    {
        boolean result = studentService.removeByIds(ids);
        return  Result.ok(result);

    }


}
