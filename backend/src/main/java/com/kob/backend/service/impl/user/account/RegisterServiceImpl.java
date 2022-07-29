package com.kob.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmPassword) {
        Map<String, String> map = new HashMap<>();
        if (username == null || username.equals("")) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (password == null || confirmPassword == null || password.equals("") || confirmPassword.equals("")) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        if (!password.equals(confirmPassword)) {
            map.put("error_message", "俩次密码不同");
            return map;
        }
        username = username.trim();
        if (username.length() > 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }
        if (password.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }
        String encodePassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/184248_lg_6ff08d15d0.jpg";
        User user = new User(null, username, encodePassword, photo);
        userMapper.insert(user);
        map.put("error_message", "success");
        return map;
    }
}
