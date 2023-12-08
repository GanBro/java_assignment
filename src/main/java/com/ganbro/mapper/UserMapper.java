package com.ganbro.mapper;

import com.ganbro.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users where username LIKE CONCAT('%', #{query}, '%')")
    List<User> searchUsersByQuery(String query);
}
