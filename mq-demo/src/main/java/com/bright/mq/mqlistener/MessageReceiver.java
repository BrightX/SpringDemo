package com.bright.mq.mqlistener;

import com.bright.mq.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Bright Xu
 * @since 2023/1/1
 */
@Component
@Slf4j
public class MessageReceiver {
    @RabbitListener(queuesToDeclare = {@Queue("hello")})
    public void listener(Message message) {
        log.info("收到消息：" + new String(message.getBody()));
    }

    @RabbitListener(queuesToDeclare = {@Queue("helloUser")})
    public void listenerUser(UserDTO userDTO) {
        log.info("收到消息：" + userDTO);
    }
}
