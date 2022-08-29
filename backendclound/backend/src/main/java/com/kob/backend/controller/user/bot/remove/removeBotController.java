package com.kob.backend.controller.user.bot.remove;

import com.kob.backend.service.user.bot.remove.RemoveBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class removeBotController {
    @Autowired
    RemoveBotService removeBotService;

    @PostMapping("/user/bot/remove/")
    Map<String, String> removeBot(@RequestParam Map<String, String> data) {
        return removeBotService.removeBot(data);
    }
}
