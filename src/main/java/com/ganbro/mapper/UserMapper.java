package com.ganbro.mapper;

import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user_info where username LIKE CONCAT('%', #{query}, '%')")
    List<UserInfo> searchUserInfoByQuery(String query);

    @Delete("DELETE FROM user_info WHERE user_id = #{userId}")
    void deleteUserById(Integer userId);
}
