package com.gdpu.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AddFriend
{
    private Long id;
    private  Long fromId;
    private Long toId;

    //0:待处理，1：已过期，2：同意申请，3：拒绝
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
