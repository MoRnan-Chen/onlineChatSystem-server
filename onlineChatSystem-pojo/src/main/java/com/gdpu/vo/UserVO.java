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
    private String username;// 用户名
    private String nickname;// 昵称
    private String email;// 邮箱
    private String userPic;// 用户头像

}
