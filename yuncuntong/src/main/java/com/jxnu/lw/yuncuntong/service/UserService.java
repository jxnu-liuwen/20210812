package com.jxnu.lw.yuncuntong.service;

import com.jxnu.lw.yuncuntong.entity.User;

public interface UserService {

    void deleteByUserId(int userId);

    User findUserByUsername(String username);

    void addUser(User user);

    User findUserByEmail(String email);

    User findByUsernameAndPassword(String username, String password);
}
