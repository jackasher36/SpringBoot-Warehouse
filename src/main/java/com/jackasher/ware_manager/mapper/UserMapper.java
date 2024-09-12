package com.jackasher.ware_manager.mapper;

import com.jackasher.ware_manager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jackasher
 * @version 1.0
 * @className UserMapper
 * @since 1.0
 **/

public interface UserMapper {

//    @Select("SELECT * FROM user_info\n" +
//            "        WHERE user_code = #{userCode} and is_delete = 0")
    public User findUserByCode(String userCode);
}
