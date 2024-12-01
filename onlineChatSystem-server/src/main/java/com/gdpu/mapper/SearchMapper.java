package com.gdpu.mapper;

import com.gdpu.vo.GroupVO;
import com.gdpu.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper
{
    //根据关键字搜索用户
    @Select("select id,username,email,user_pic,sex from user where username like concat('%',#{keyword},'%') ")
    List<UserVO> searchUsers(String keyword);

    //根据关键字搜索群
    @Select("select id,group_name,group_pic,group_desc from group where group_name like concat('%',#{keyword},'%') ")
    List<GroupVO> searchGroups(String keyword);
}
