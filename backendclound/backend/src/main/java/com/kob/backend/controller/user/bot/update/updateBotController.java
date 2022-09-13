package com.kob.backend.controller.user.bot.update;

import com.kob.backend.service.user.bot.update.updateBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class updateBotController {
    @Autowired
    private updateBotService updateBotService;

    @PostMapping("/api/user/bot/update/")
    public Map<String, String> updateBot(@RequestParam Map<String, String> data) {
        return updateBotService.updateBot(data);
    }

}
