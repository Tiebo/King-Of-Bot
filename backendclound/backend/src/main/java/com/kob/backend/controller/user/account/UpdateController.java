package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateController {

    @Autowired
    UpdateService updateService;

    @PostMapping("/api/user/account/info/update/")
    Map<String, String> updateUserInfo(@RequestParam Map<String, String> data) {
        return updateService.updateUserInfo(data);
    }
}
