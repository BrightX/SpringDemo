package com.bright.controller;

import com.bright.domain.entity.User;
import com.bright.domain.query.UserQuery;
import com.bright.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@RestController
@RequestMapping("/")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/findUsersByName")
    public List<User> findUsersByName(String name) {
        return userService.findUsersByName(name);
    }

    @RequestMapping("/queryUser")
    public List<User> findUsersByQuery(UserQuery query) {
        return userService.findUsers(query);
    }
}
