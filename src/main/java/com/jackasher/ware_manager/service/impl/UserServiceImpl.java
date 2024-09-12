package com.jackasher.ware_manager.service.impl;

import com.jackasher.ware_manager.entity.User;
import com.jackasher.ware_manager.mapper.UserMapper;
import com.jackasher.ware_manager.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jackasher
 * @version 1.0
 * @className UserServiceImpl
 * @since 1.0
 **/

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByCode(String userCode) {

        System.out.println("userMapper" +  userMapper);
        User userByCode = userMapper.findUserByCode(userCode);
        System.out.println(userByCode);
        return userByCode;
    }
}
