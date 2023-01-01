package com.bright.mq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐亮亮
 * @since 2022/11/10
 */
@Configuration
public class MessageQueueConfig {
    /**
     * 需要注入这个类用于将对象转换为json。注意函数名不可为 messageReceiver，因为该名称的 Bean 已经被注入了。
     * 当从任意 Java 对象转换为消息时，默认以 SimpleMessageConverter 处理字节数组、字符串和 Serializable 实例。
     * 它将其中的每一个转换为字节（对于字节数组，没有什么可转换的），并相应地设置内容类型属性。
     * 如果要转换的对象与这些类型之一不匹配，则消息正文为空。
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
