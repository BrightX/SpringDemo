package com.bright.websocket.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @see <a href="https://zhuanlan.zhihu.com/p/514468081">传统@ServerEndpoint方式开发WebSocket应用和SpringBoot构建WebSocket应用程序 - 知乎</a>
 * @since 2023/8/6
 */
@Component
@Slf4j
public class MyHandler extends TextWebSocketHandler {
    /**
     * 在 WebSocket 协商成功且 WebSocket 连接打开并可供使用后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("连接已建立");
    }

    /**
     * 在任一端关闭 WebSocket 连接后或发生传输错误后调用。
     * 尽管会话在技术上可能仍处于打开状态，但根据基础实现，此时不建议发送消息，并且很可能不会成功。
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("连接已关闭");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        log.info("收到消息: " + payload);
        if ("ping".equals(payload)) {
            session.sendMessage(new TextMessage("pong"));
        }
    }
}
