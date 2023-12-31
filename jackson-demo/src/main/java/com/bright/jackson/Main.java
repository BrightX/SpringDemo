package com.bright.jackson;

import com.bright.jackson.pojo.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        User user = new User(1, "zhangSan", "张三", 18, "男");
        String json = JsonUtil.stringify(user);
        System.out.println("json = " + json);

        String json1 = "{\"id\":2,\"username\":\"lisi\",\"nickname\":\"李思\",\"age\":16,\"gender\":\"女\"}";
        User user1 = JsonUtil.parse(json1, User.class);
        System.out.println("user1 = " + user1);

        Map<String, Object> user2 = JsonUtil.parse(json1, HashMap.class);
        System.out.println("user2 = " + user2);
    }
}
