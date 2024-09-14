package com.jackasher.ware_manager.service;

/**
 * @author Jackasher
 * @version 1.0
 * @className AuthService
 * @since 1.0
 **/

import com.jackasher.ware_manager.dto.AssignAuthDto;
import com.jackasher.ware_manager.entity.Auth;

import java.util.List;

public interface AuthService {

    //根据用户id查询用户权限(菜单)树的业务方法
    public List<Auth> findAuthTree(int userId);

    //查询整个权限(菜单)树的业务方法
    public List<Auth> allAuthTree();

    //给角色分配权限(菜单)的业务方法
    public void assignAuth(AssignAuthDto assignAuthDto);


}

