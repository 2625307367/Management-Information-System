package com.wangpeng.zhxydemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_teacher")
public class Teacher {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("tno")
    private String sno;
    private String name;
    private char gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    @TableField("portrait_path")
    private String portraitPath;
    @TableField("clazz_name")
    private String clazzName;
}
