package com.bright.data.db2.mapper;

import com.bright.data.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户实体对象
     */
    User findUserByUsername(String username);

    /**
     * 搜索用户
     *
     * @param name 用户名或者名称
     * @return 搜索结果
     */
    List<User> findUserByNameLike(String name);

    /**
     * 修改用户密码
     */
    int changePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 修改用户名称
     */
    int changeNickname(@Param("username") String username, @Param("nickname") String nickname);

    /**
     * 修改用户头像
     *
     * @param username 用户名
     * @param avatar   头像路径
     * @return 修改结果
     */
    int changeAvatar(@Param("username") String username, @Param("avatar") String avatar);

    /**
     * 修改用户邮箱
     *
     * @param username 用户名
     * @param email    电子邮箱
     * @return 修改结果
     */
    int changeEmail(@Param("username") String username, @Param("email") String email);

    /**
     * 判断用户是否存在
     */
    boolean exist(String username);

    /**
     * 添加用户
     */
    int createUser(@Param("user") User user);

    /**
     * 通过用户名删除用户
     */
    int deleteByUsername(String username);

    /**
     * 通过用户id删除用户
     */
    int deleteByUserId(long userId);
}
