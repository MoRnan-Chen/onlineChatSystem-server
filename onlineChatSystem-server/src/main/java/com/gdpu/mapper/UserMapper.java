package com.gdpu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdpu.dto.PasswordDTO;
import com.gdpu.dto.UserDTO;
import com.gdpu.entity.User;
import com.gdpu.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Update("update user set nickname = #{nickname},email = #{email},user_pic = #{userPic},update_time = #{updateTime} where username = #{username}")
    void update(UserDTO userDTO);
    //更新密码
    @Update("update user set password = #{newPassword},update_time = #{updateTime} where username = #{username}")
    void updatePwd(PasswordDTO passwordDTO);
}
