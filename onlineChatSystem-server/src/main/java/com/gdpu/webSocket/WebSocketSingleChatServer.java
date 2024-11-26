package com.gdpu.webSocket;

import com.alibaba.fastjson2.JSON;
import com.gdpu.entity.SingleMessage;
import com.gdpu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint(value = "/singleChat/{id}")
@Slf4j
public class WebSocketSingleChatServer implements ApplicationContextAware
{
    // 保存所有用户的session
    private static Map<Long, Session> sessionMap = new HashMap<>();

    // 全局静态变量，保存 ApplicationContext
    private static ApplicationContext applicationContext;
    // 注入service
    private UserService userService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        WebSocketSingleChatServer.applicationContext = applicationContext;
    }


    @OnOpen
    public void onOpen(Session session,@PathParam("id") Long id)
    {

        log.info("用户id：{}连接成功",id);
        // 将用户id和session存入map
        sessionMap.put(id,session);
    }
    @OnClose
    public void onClose(@PathParam("id") Long id)
    {
        log.info("{}用户退出",id);
        //移出map
        sessionMap.remove(id);
    }
    @OnMessage
    public void onMessage(String message)
    {
        log.info("用户发送的消息：{}",message);
        SingleMessage messageJSON = JSON.parseObject(message,SingleMessage.class);
        messageJSON.setCreateTime(LocalDateTime.now());
        //获取service
        userService = WebSocketSingleChatServer.applicationContext.getBean(UserService.class);
        // 保存聊天记录
        userService.insertChatRecord(messageJSON);
        sendMessage(messageJSON.getToId(),messageJSON);

    }

    public static void sendMessage(Long id,SingleMessage message)
    {
        Session session = sessionMap.get(id);
        if (session != null)
        {
            session.getAsyncRemote().sendText(JSON.toJSONString(message));
        }
    }

}
