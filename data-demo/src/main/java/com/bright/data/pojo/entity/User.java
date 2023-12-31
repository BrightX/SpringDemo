package com.bright.data.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "user", indexes = {
    @Index(name = "idx_username", columnList = "username", unique = true)
})
public class User extends BaseDO {
    @Column(columnDefinition = "VARCHAR(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL")
    private String username;
    private String nickname;
    @Column(columnDefinition = "VARCHAR(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL")
    private String password;
    /**
     * 头像
     */
    @Column(length = 512)
    private String avatar;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 团队id
     */
    private Long teamId;

    /**
     * 用户所在团队
     */
    @Transient
    private Team team;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", nickname='" + nickname + '\'' +
            ", id=" + id +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", removed=" + removed +
            '}';
    }
}
