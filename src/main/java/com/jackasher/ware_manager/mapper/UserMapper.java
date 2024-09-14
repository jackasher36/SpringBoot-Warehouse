package com.jackasher.ware_manager.mapper;

import com.jackasher.ware_manager.entity.User;
import com.jackasher.ware_manager.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jackasher
 * @version 1.0
 * @className UserMapper
 * @since 1.0
 **/

public interface UserMapper {

    //    @Select("SELECT * FROM user_info\n" +
//            "        WHERE user_code = #{userCode} and is_delete = 0")
//根据用户名查找用户的方法
    public User findUserByCode(String userCode);

    //查询用户总行数的方法
    public int selectUserCount(User user);

    //分页查询用户的方法
    public List<User> selectUserPage(@Param("page") Page page, @Param("user") User user);

    //添加用户的方法
    public int insertUser(User user);

    //根据用户id修改用户状态的方法
    public int updateUserState(User user);

    //根据用户id将用户状态修改为删除状态
    public int setUserDelete(Integer userId);

    //根据用户id修改用户昵称的方法
    public int updateNameById(User user);

    //根据用户id修改密码的方法
    public int updatePwdById(User user);


}
