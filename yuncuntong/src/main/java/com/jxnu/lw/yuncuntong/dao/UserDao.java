package com.jxnu.lw.yuncuntong.dao;

import com.jxnu.lw.yuncuntong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findUserByEmail(String email);

    User findByUsernameAndPassword(String username, String password);
}
