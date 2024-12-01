package com.gdpu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data

public class UserVO implements Serializable
{
    private Long id;//用户id
    private String username;// 用户名
    private String email;// 邮箱
    private String userPic;// 用户头像

    private String sex;// 用户性别

}
