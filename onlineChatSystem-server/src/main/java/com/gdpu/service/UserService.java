package com.gdpu.service;

import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.SingleMessage;
import com.gdpu.entity.User;
import com.gdpu.vo.SingleMessageVO;
import com.gdpu.vo.UserVO;

import java.util.ArrayList;


public interface UserService
{
    // 根据用户名查询用户
    User findUserByUsername(String username);

    //根据用户id查询用户
    User findUserById(Long id);
    // 注册新用户
    void register(UserDTO userDTO);

    //更新用户信息
    void update(UserDTO userDTO);

    // 更新密码
    void updatePwd(PasswordDTO passwordDTO);

    //更新用户头像
    void updateAvatar(String avatarUrl);

    //获取好友列表
    ArrayList<UserVO> getFriendList();
    //获取好友聊天记录
    ArrayList<SingleMessageVO> getFriendChatRecord(Long myId, Long friendId);

    //往数据库中插入聊天记录
    void insertChatRecord(SingleMessage message);
}
