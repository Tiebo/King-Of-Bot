package com.kob.backend.service.impl.user.account;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> updateUserInfo(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = userDetails.getUser();

        String username = data.get("username");
        String description = data.get("description");

        Map<String, String> map = new HashMap<>();

        if (username == null || username.equals("")) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        User new_user = new User(user.getId(), username,user.getPhoto(), description);
        int i = userMapper.updateById(new_user);
        if (i == 1){
            map.put("error_message", "success");
        } else {
            map.put("error_message", "更新失败");
        }
        return map;
    }
}
