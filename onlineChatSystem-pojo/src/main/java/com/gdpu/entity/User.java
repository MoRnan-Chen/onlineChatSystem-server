package com.gdpu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable
{
    private Long id;// 主键
    private String username;// 用户名
    private String password;// 密码
    private String nickname;// 昵称
    private String email;// 邮箱
    private String userPic;// 用户头像
    private String sex;//用户性别
    private LocalDateTime createTime;// 创建时间
    private LocalDateTime updateTime;// 更新时间

}
