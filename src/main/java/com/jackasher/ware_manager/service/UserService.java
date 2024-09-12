package com.jackasher.ware_manager.service;

import com.jackasher.ware_manager.entity.User;

/**
 * @author Jackasher
 * @version 1.0
 * @className UserService
 * @since 1.0
 **/
public interface UserService {
    public User queryUserByCode(String userCode);
}
