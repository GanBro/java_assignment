package com.ganbro.mapper;

import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user_info where username LIKE CONCAT('%', #{query}, '%')")
    Page<UserInfo> searchUserInfoByQuery(String query);

    @Delete("DELETE FROM user_info WHERE user_id = #{userId}")
    void deleteUserInfoById(Integer userId);

    void updateUserInfo(@Param("userInfo") UserInfo userInfo);

    void updateUsers(@Param("userInfo") UserInfo userInfo);

    @Insert("INSERT INTO users (username, password, is_admin) VALUES (#{username}, #{password}, #{isAdmin})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_Id")
    void insertUser(User user);

    void insertUserInfo(@Param("userInfo") UserInfo userInfo);

    @Select("SELECT * FROM user_info where username = #{username}")
    UserInfo selectUserInfo(String username);

    @Select("SELECT * FROM user_info where user_id = #{userId}")
    UserInfo selectUserInfoByUserId(int userId);

    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    void deleteUserById(Integer userId);
}
