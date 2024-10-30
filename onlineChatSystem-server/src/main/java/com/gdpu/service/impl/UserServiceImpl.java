package com.gdpu.service.impl;

import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.User;
import com.gdpu.mapper.UserMapper;
import com.gdpu.service.UserService;
import com.gdpu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User findUserByUsername(String username)
    {

        return userMapper.findUserByUsername(username);
    }

    //注册新用户
    public void register(UserDTO userDTO)
    {
        //构建用户对象
        User user = User.builder().username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword())).createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now()).build();
        //注册新用户
        userMapper.add(user);
    }

    //更新用户信息
    public void update(UserDTO userDTO)
    {
        userDTO.setUpdateTime(LocalDateTime.now());
        userMapper.update(userDTO);
    }

    // 更新密码
    public void updatePwd(PasswordDTO passwordDTO)
    {
        //设置更新时间
        passwordDTO.setUpdateTime(LocalDateTime.now());
        //对新密码进行加密
        passwordDTO.setNewPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userMapper.updatePwd(passwordDTO);
    }

}
