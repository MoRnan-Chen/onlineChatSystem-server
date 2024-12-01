package com.gdpu.service.impl;

import com.gdpu.mapper.SearchMapper;
import com.gdpu.service.SearchService;
import com.gdpu.vo.GroupVO;
import com.gdpu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService
{
    @Autowired
    public SearchMapper searchMapper;
    // 搜索用户
    public List<UserVO> searchUsers(String keyword)
    {
        return searchMapper.searchUsers(keyword);
    }

    // 搜索群组
    public List<GroupVO> searchGroups(String keyword)
    {
        return searchMapper.searchGroups(keyword);
    }
}
