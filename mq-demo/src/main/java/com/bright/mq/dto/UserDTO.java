package com.bright.mq.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 徐亮亮
 * @since 2023/1/1
 */
@Getter
@Setter
public class UserDTO {
    protected Long id;
    protected String username;
    protected Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
