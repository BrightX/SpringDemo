package com.bright.data.controller;

import com.bright.data.db2.mapper.UserMapper;
import com.bright.data.mapper.TeamMapper;
import com.bright.data.pojo.entity.Team;
import com.bright.data.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
@Transactional(rollbackFor = Exception.class)
public class UserController {
    private UserMapper userMapper;
    private TeamMapper teamMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setTeamMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    @GetMapping("/findUserByName")
    public List<User> findUserByName(String name) {
        if (ObjectUtils.isEmpty(name)) {
            return Collections.emptyList();
        }
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }
        return userMapper.findUserByNameLike(name);
    }

    @GetMapping("/findAllTeam")
    public List<Team> findAllTeam() {
        return teamMapper.findAll();
    }
}
