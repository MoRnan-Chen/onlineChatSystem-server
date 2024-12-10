package com.gdpu.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendList
{
    private Long id;
    private Long userId;
    private Long friendId;
    private LocalDateTime createTime;
}
