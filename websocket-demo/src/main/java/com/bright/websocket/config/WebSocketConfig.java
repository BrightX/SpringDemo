package com.bright.websocket.config;

import com.bright.websocket.ws.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private MyHandler myHandler;

    @Autowired
    public void setMyHandler(MyHandler myHandler) {
        this.myHandler = myHandler;
    }

    /**
     * Register {@link org.springframework.web.socket.WebSocketHandler WebSocketHandlers} including SockJS fallback options if desired.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler, "/myHandler")
            .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    /**
     * 自动注册 @ServerEndpoint 注解声明的对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
