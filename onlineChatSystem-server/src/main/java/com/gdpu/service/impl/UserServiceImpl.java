package com.gdpu.service.impl;

import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.AddFriend;
import com.gdpu.entity.FriendList;
import com.gdpu.entity.SingleMessage;
import com.gdpu.entity.User;
import com.gdpu.mapper.UserMapper;
import com.gdpu.result.Result;
import com.gdpu.service.UserService;
import com.gdpu.utils.UserContext;
import com.gdpu.vo.SingleMessageVO;
import com.gdpu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

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

    //根据id查询用户信息
    public User findUserById(Long id)
    {
        return userMapper.selectById(id);
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
        Long currentId = UserContext.getCurrentId();
        userDTO.setId(currentId);
        System.out.println(userDTO.getId());
        userMapper.update(userDTO);
    }

    // 更新密码
    public void updatePwd(PasswordDTO passwordDTO)
    {
        //设置更新时间
        passwordDTO.setUpdateTime(LocalDateTime.now());
        //用于用户名查询
        passwordDTO.setId(UserContext.getCurrentId());
        //对新密码进行加密
        passwordDTO.setNewPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userMapper.updatePwd(passwordDTO);
    }

    //更新用户头像
    public void updateAvatar(String avatarUrl)
    {
        userMapper.updateAvatar(avatarUrl,UserContext.getCurrentId(),LocalDateTime.now());
    }

    //获取好友列表
    public ArrayList<UserVO> getFriendList()
    {
        return userMapper.selectFriendList(UserContext.getCurrentId());
    }

    //获取好友聊天记录
    public ArrayList<SingleMessageVO> getFriendChatRecord(Long myId, Long friendId)
    {
        return userMapper.selectFriendChatRecord(myId,friendId);
    }

    //插入聊天记录
    public void insertChatRecord(SingleMessage message)
    {
        userMapper.insertChatRecord(message);
    }

    //搜索好友
    public ArrayList<UserVO> searchUsers(String keyword)
    {
        return userMapper.searchUsers(keyword);
    }



    //查询好友列表
    public FriendList findFriendList(Long currentId, Long userId)
    {
        //查询好友列表
        FriendList friendList = userMapper.findFriendList(currentId,userId);
        return friendList;
    }
    
    
    /**
     * 添加好友到好友列表
     * @param currentId
     * @param userId
     */
    // Service 实现
    @Transactional
    @Override
    public void addFriendList(Long currentId, Long userId) {
        // 检查是否已经是好友
        int existingFriendCount = userMapper.checkFriendExists(currentId, userId);
        if (existingFriendCount > 0) {
            throw new RuntimeException("已经是好友");
        }
        // 创建添加好友列表记录
        AddFriend addFriendList = AddFriend.builder()
                .fromId(currentId)
                .toId(userId)
                .createTime(LocalDateTime.now())
                .build();
        
        userMapper.addFriendList(addFriendList);
        
        // 创建添加好友申请记录
        AddFriend addFriend = AddFriend.builder()
                .fromId(currentId)
                .toId(userId)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        
        userMapper.addFriend(addFriend);
    }
    
    
    

}
