package com.bright.schedule.task;

import com.bright.schedule.domain.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bright Xu
 * @since 2023/1/1
 */
@Component
@Slf4j
public class MyTask {
    private AmqpTemplate amqpTemplate;

    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Scheduled(fixedDelay = 15, initialDelay = 500)
    public void handler() {
        log.info("定时任务执行...");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(System.currentTimeMillis());
        userDTO.setUsername("Tom");
        userDTO.setAge(18);
        amqpTemplate.convertAndSend("helloUser", userDTO);
    }
}
