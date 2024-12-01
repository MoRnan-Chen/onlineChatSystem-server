package com.gdpu.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupVO implements Serializable
{
    // id
    private Long id;
    // 群名
    private String groupName;
    // 群头像
    private String groupPic;
    // 群描述
    private String groupDesc;

}
