package com.jxnu.lw.yuncuntong.service.impl;

import com.jxnu.lw.yuncuntong.dao.UserDao;
import com.jxnu.lw.yuncuntong.entity.User;
import com.jxnu.lw.yuncuntong.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void deleteByUserId(int userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }


}
