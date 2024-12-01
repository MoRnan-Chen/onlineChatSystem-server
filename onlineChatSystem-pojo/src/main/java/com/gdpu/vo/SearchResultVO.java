package com.gdpu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResultVO implements Serializable
{
    private List<UserVO> users;   // 存放搜索到的用户
    private List<GroupVO> groups;  // 存放搜索到的群聊
}
