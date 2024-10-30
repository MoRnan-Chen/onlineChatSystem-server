package com.gdpu.service;

import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.User;



public interface UserService
{
    // 根据用户名查询用户
    User findUserByUsername(String username);
    // 注册新用户
    void register(UserDTO userDTO);

    //更新用户信息
    void update(UserDTO userDTO);

    // 更新密码
    void updatePwd(PasswordDTO passwordDTO);
}
