package com.gdpu.service;

import com.gdpu.vo.GroupVO;
import com.gdpu.vo.UserVO;

import java.util.List;

public interface SearchService
{
    //根据关键词搜索用户
    List<UserVO> searchUsers(String keyword);

    //根据关键词搜索群
    List<GroupVO> searchGroups(String keyword);
}
