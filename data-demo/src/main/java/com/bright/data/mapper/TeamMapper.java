package com.bright.data.mapper;

import com.bright.data.pojo.entity.Team;
import com.bright.data.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {

    List<Team> findAll();

    Team findByUserId(@Param("userId") Long userId);

    List<User> findTeamUser(@Param("teamId") Long teamId);

    int joinTeam(@Param("userId") Long userId, @Param("teamId") Long teamId);
}
