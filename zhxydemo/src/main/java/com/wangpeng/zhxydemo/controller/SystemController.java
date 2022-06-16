package com.wangpeng.zhxydemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangpeng.zhxydemo.Utils.*;
import com.wangpeng.zhxydemo.pojo.Admin;
import com.wangpeng.zhxydemo.pojo.Student;
import com.wangpeng.zhxydemo.pojo.Teacher;
import com.wangpeng.zhxydemo.pojo.loginForm;
import com.wangpeng.zhxydemo.service.AdminService;
import com.wangpeng.zhxydemo.service.StudentService;
import com.wangpeng.zhxydemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sms/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;


    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage image = CreateVerifiCodeImage.getVerifiCodeImage();
        String code = new String(CreateVerifiCodeImage.getVerifiCode());
        request.getSession().setAttribute("VerifiCode", code);
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/login")
    public Result login(@RequestBody loginForm loginForm, HttpServletRequest request) {
        String code = loginForm.getVerifiCode();
        HttpSession session = request.getSession();
        String SesionCode = (String) session.getAttribute("VerifiCode");

        if ("".equals(code) || SesionCode == null) {
            return Result.fail().message("验证码失效！");
        }
        if (!code.equalsIgnoreCase(SesionCode)) {
            return Result.fail().message("验证码错误！");
        }
        session.removeAttribute("VerifiCode");
        switch (loginForm.getUserType()) {

            case 1:
                Map<String, Object> Adminmap = new LinkedHashMap<>();
                try {

                    Admin admin = adminService.login(loginForm);
                    if (admin != null && admin.getPassword().equals(MD5.encrypt(loginForm.getPassword()))) {
                        Adminmap.put("token", JwtHelper.createToken(admin.getId().longValue(), 1));

                    } else {
                        throw new RuntimeException("账号或者密码错误");
                    }
                    return Result.ok(Adminmap);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }

            case 2:
                Map<String, Object> Studentmap = new LinkedHashMap<>();
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        Studentmap.put("token", JwtHelper.createToken(student.getId().longValue(), 2));
                    } else {
                        throw new RuntimeException("账号或者密码错误");
                    }
                    return Result.ok(Studentmap);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                Map<String, Object> Teachmap = new LinkedHashMap<>();

                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        Teachmap.put("token", JwtHelper.createToken(teacher.getId().longValue(), 3));
                    } else {
                        throw new RuntimeException("账号或者密码错误！");
                    }
                    return Result.ok(Teachmap);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("查不到此用户！");
    }
    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader String token)
    {
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration)
        {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        switch (JwtHelper.getUserType(token))
        {
            case 1:
                Admin admin= adminService.getAdminById(JwtHelper.getUserId(token));
                map.put("userType",1);
                map.put("user",admin);
                break;
            case 2:
                Student student= studentService.getStudentById(JwtHelper.getUserId(token));
                map.put("userType",2);
                map.put("user",student);
                break;
            case 3:
                Teacher teacher= teacherService.getTeacherById(JwtHelper.getUserId(token));
                map.put("userType",3);
                map.put("user",teacher);
                break;

        }

        return Result.ok(map);
    }

   @PostMapping("/headerImgUpload")
    public  Result headerImgUpload(@RequestPart MultipartFile multipartFile)
   {
       String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
       String oldName = multipartFile.getOriginalFilename();
       int i = oldName.lastIndexOf(".");
        String newName= uuid.concat(oldName.substring(i)) ;
        String path= "C:/Users/WangPeng/Desktop/zhxydemo/target/classes/public/upload".concat(newName);
       try {
           multipartFile.transferTo(new File(path));
       } catch (IOException e) {
           e.printStackTrace();
       }
       String realPath = "/upload".concat(newName);
       return Result.ok(realPath);
   }
   //updatePwd/admin/123456
    @PostMapping("/updatePwd/{oldPassword}/{newPassword}")
    public Result updatePwd(
         @PathVariable("oldPassword")   String oldPassword,
         @PathVariable("newPassword")   String newPassword,
          @RequestHeader   String token

    )
    {
        if (JwtHelper.isExpiration(token)) {
            return Result.fail().message("token失效");
        }

        switch (JwtHelper.getUserType(token))
        {
            case 1:

                Admin admin=adminService.updateBypassword(JwtHelper.getUserId(token).intValue(),oldPassword);
                if (admin!=null)
                {
                    admin.setPassword(MD5.encrypt(newPassword));
                    adminService.saveOrUpdate(admin);
                }
                else
                {
                    return  Result.fail().message("查找不到数据");
                }
                break;
            case 2:

                Student student =studentService.updateBypassword(JwtHelper.getUserId(token).intValue(),oldPassword);
                if (student!=null)
                {
                    student.setPassword(MD5.encrypt(newPassword));
                    studentService.saveOrUpdate(student);
                }
                else
                {
                    return  Result.fail().message("查找不到数据");
                }
                break;
            case 3:
                 Teacher teacher= teacherService.updateBypassword(JwtHelper.getUserId(token).intValue(),oldPassword);
                 if (teacher!=null)
                 {
                     teacher.setPassword(MD5.encrypt(newPassword));
                     teacherService.saveOrUpdate(teacher);
                 }
                break;


        }
        return Result.ok();
    }

}
