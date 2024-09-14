package com.jackasher.ware_manager.controller;


import com.jackasher.ware_manager.entity.Auth;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.service.AuthService;
import com.jackasher.ware_manager.utils.CurrentUser;
import com.jackasher.ware_manager.utils.TokenUtils;
import com.jackasher.ware_manager.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    //注入AuthService
    @Autowired
    private AuthService authService;

    /**
     * 查询整个权限(菜单)树的url接口/auth/auth-tree
     */
    @RequestMapping("/auth-tree")
    public Result allAuthTree(){
        //执行业务
        List<Auth> allAuthTree = authService.allAuthTree();
        //响应
        return Result.ok(allAuthTree);
    }

    @Autowired
    private TokenUtils tokenUtils;

    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);

        int userId = currentUser.getUserId();
        List<Auth> authTree = authService.findAuthTree(userId);
        return  Result.ok(authTree);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)  String token){
        stringRedisTemplate.delete(token);
        return Result.ok("退出系统!");
    }

}
