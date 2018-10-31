package com.example.sell.demo.service;/*
 *
 * @author lileilei
 * Date: 2018/10/26
 * Time: 13:05
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSockets {
    private Session session;
    private static CopyOnWriteArraySet<WebSockets> webSockets=new CopyOnWriteArraySet<>();
    @OnOpen
    public  void onOpen(Session session){
        this.session=session;
        webSockets.add(this);
        log.info("【webscoket链接】有新的接入，总数：{}",webSockets.size());
    }
    @OnClose
    public  void  onclose(Session session){
        webSockets.remove(this);
        log.info("【webscoket链接】,链接断开，");
    }
    @OnMessage
    public  void  onMessage(String message){
        log.info("【webscoket消息】,收到客户端发来的消息：{}",message);
    }
    public static void  sendMessage(String message){
        for(WebSockets webSocket:webSockets){
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
