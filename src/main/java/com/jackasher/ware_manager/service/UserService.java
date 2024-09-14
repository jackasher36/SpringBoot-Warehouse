package com.jackasher.ware_manager.service;

import com.jackasher.ware_manager.page.Page;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.entity.User;


/**
 * @author Jackasher
 * @version 1.0
 * @className UserService
 * @since 1.0
 **/
public interface UserService {

    public User queryUserByCode(String usserCode);
    //根据用户名查找用户的业务方法
    public User findUserByCode(String userCode);

    //分页查询用户的业务方法
    public Page queryUserPage(Page page, User user);

    //添加用户的业务方法
    public Result saveUser(User user);

    //修改用户状态的业务方法
    public Result updateUserState(User user);

    //根据用户id删除用户的业务方法
    public int deleteUserById(Integer userId);

    //修改用户昵称的业务方法
    public Result updateUserName(User user);

    //重置密码的业务方法
    public Result resetPwd(Integer userId);
}
