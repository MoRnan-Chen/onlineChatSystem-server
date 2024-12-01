package com.gdpu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupDTO implements Serializable
{
    // 群名
    private String groupName;
    // 群头像
    private String groupPic;
    // 群描述
    private String groupDesc;
}
