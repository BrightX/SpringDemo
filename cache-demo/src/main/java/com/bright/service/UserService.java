package com.bright.service;

import com.bright.domain.entity.User;
import com.bright.domain.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@Service
@CacheConfig(cacheNames = "userList", keyGenerator = "CacheKeyGenerator")
@Slf4j
public class UserService {
    private final List<User> users = new ArrayList<>();

    {
        users.add(new User(1, "u1", "user1", "2003-01-01"));
        users.add(new User(2, "u2", "user2", "2004-01-01"));
        users.add(new User(3, "u3", "user3", "2005-01-01"));
        users.add(new User(4, "u4", "user4", "2006-01-01"));
        users.add(new User(5, "u5", "user5", "2007-01-01"));
        users.add(new User(6, "u6", "user6", "2008-01-01"));
        users.add(new User(7, "u7", "user7", "2009-01-01"));
        users.add(new User(8, "u8", "user8", "2000-01-01"));
        users.add(new User(9, "u9", "user9", "2001-01-01"));
        users.add(new User(10, "u10", "user10", "2002-01-01"));
        users.add(new User(11, "u11", "user11", "2010-01-01"));
        users.add(new User(12, "u12", "user12", "2011-01-01"));
        users.add(new User(13, "u13", "user13", "2012-01-01"));
    }

    @Cacheable
    public List<User> findAllUsers() {
        log.info("未使用缓存，直接查询, findAllUsers");
        return users;
    }

    @Cacheable
    public List<User> findUsersByName(String name) {
        log.info("未使用缓存，直接查询, findUsersByName: name = {}", name);
        return users.stream()
            .filter(user -> user.getName().startsWith(name))
            .collect(Collectors.toList());
    }

    @Cacheable
    public List<User> findUsers(UserQuery query) {
        log.info("未使用缓存，直接查询, findUsers: query = {}", query);
        return users.stream()
            .filter(user -> {
                if (StringUtils.isNotEmpty(query.getName()) && !user.getName().startsWith(query.getName())) {
                    return false;
                }
                if (StringUtils.isNotEmpty(query.getUsername()) && !query.getUsername().equals(user.getUsername())) {
                    return false;
                }
                if (query.getBirthdayGt() != null && !query.getBirthdayGt().before(user.getBirthday())) {
                    return false;
                }
                return query.getBirthdayLt() == null || query.getBirthdayLt().after(user.getBirthday());
            })
            .collect(Collectors.toList());
    }
}
