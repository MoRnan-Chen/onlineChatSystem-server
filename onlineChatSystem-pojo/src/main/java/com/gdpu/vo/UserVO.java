package com.gdpu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO
{
    private Long id;//用户id
    private String username;// 用户名
    private String email;// 邮箱
    private String userPic;// 用户头像

    private String sex;// 用户性别

}
