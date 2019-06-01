package com.lgc.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @program: javaConfig-ssm
 * @description: pojo user
 * @author: lgc
 * @create: 2019-06-01 17:45
 **/
@Data
@TableName("blog_user")
public class User {
    private static final long serialVersionUID = -6875995068523214043L;
    @TableId
    private Integer userId;

    private String userCode;

    private String userName;

    private String userPwd;

    private String mail;

    private String addr;

    private String born;

    private Date inDate;

    private Date createDate;

    private Integer userType;

    private String userStatus;

}
