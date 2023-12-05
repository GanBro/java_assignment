package com.ganbro.mapper;

import com.ganbro.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select  * from users where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(String username, String password);
}
