package com.jxnu.lw.yuncuntong;

import com.jxnu.lw.yuncuntong.dao.UserDao;
import com.jxnu.lw.yuncuntong.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YuncuntongApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void add2() {
        User user = new User();
        user.setUsername("张三三");
        user.setPassword("123456");
        user.setEmail("123456@qq.com");
        userDao.save(user);
    }

}
