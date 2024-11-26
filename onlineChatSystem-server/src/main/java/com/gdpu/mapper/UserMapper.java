package com.gdpu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.SingleMessage;
import com.gdpu.entity.User;
import com.gdpu.vo.SingleMessageVO;
import com.gdpu.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Mapper
public interface UserMapper
{
    //根据用户名来查询
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    //插入新用户
    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},#{createTime},#{updateTime})")
    void add(User user);

    //更新用户信息
    @Update("update user set username=#{username},sex = #{sex}, email = #{email},update_time = #{updateTime} where id = #{id}")
    void update(UserDTO userDTO);

    //更新密码
    @Update("update user set password = #{newPassword},update_time = #{updateTime} where username = #{username}")
    void updatePwd(PasswordDTO passwordDTO);

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User selectById(Long id);

    //更新头像
    @Update("update user set user_pic = #{avatarUrl},update_time = #{updateTime} where username = #{username}")
    void updateAvatar(String avatarUrl,String username,LocalDateTime updateTime);

    //获取好友列表
    @Select("select user.id,user.username,user.email,user.user_pic,user.sex from firend_list,user where firend_list.user_id = #{id} and user.id=firend_list.firend_id")
    ArrayList<UserVO> selectFriendList(long id);

    //获取好友聊天记录
    @Select("select content,from_id,to_id,type,create_time from single_message where (from_id = #{myId} and to_id = #{friendId}) or (from_id = #{friendId} and to_id = #{myId}) order by create_time ")
    ArrayList<SingleMessageVO> selectFriendChatRecord(Long myId, Long friendId);

    //插入聊天记录
    @Insert("insert into single_message(content,from_id,to_id,type,create_time) values(#{content},#{fromId},#{toId},#{type},#{createTime})")
    void insertChatRecord(SingleMessage message);
}
