package com.bright.data.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 团队
 */
@Getter
@Setter
@Entity
public class Team extends BaseDO {
    private String name;
    /**
     * 创建者id
     */
    private Long creatorId;
    /**
     * 创建者
     */
    @Transient
    private User creator;

    /**
     * 团队负责人id
     */
    private Long leaderId;
    /**
     * 团队负责人
     */
    @Transient
    private User leader;
}
