package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/user/all/")
    public List<User> getAll() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @GetMapping("/user/{userId}/")
    public User getuser(@PathVariable int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return userMapper.selectOne(queryWrapper);
    }

    @GetMapping("/user/add/{userId}/{username}/{password}/")
    public String insertUser(@PathVariable int userId,
                             @PathVariable String username,
                             @PathVariable String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        User user = new User(userId, username, encode);
        userMapper.insert(user);
        return "Successful!";
    }

    @GetMapping("/user/delete/{userId}/")
    public String delete(@PathVariable int userId) {
        userMapper.deleteById(userId);
        return "Successful!";
    }
}
