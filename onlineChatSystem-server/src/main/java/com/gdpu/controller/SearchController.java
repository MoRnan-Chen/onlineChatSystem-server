package com.gdpu.controller;

import com.gdpu.result.Result;
import com.gdpu.service.SearchService;
import com.gdpu.vo.GroupVO;
import com.gdpu.vo.SearchResultVO;
import com.gdpu.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.directory.SearchResult;
import java.util.List;

@RestController
@Slf4j
public class SearchController
{
    @Autowired
    public SearchService searchService;
    //搜索好友或群聊
    @GetMapping("/search")
    public Result search(@RequestParam String keyword)
    {
        log.info("搜索好友或群聊：{}",keyword);

        // 调用搜索服务，获取用户和群聊数据
        List<UserVO> users = searchService.searchUsers(keyword);
        List<GroupVO> groups = searchService.searchGroups(keyword);

        // 将结果封装成统一的 SearchResult 对象
        SearchResultVO searchResultVO = SearchResultVO.builder().users(users).groups(groups).build();
        return Result.success(searchResultVO);
    }
}
