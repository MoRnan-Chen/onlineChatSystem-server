package com.gdpu.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class SingleMessageVO
{

    String content;//信息内容
    Long fromId;//发送者id
    Long toId;//接收者id
    Integer type;//0为文本，1为文件
    LocalDateTime createTime;//发送时间
}

