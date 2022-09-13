package com.kob.backend.controller.user.bot.add;

import com.kob.backend.service.user.bot.add.AddBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class addBotController {
    @Autowired
    private AddBotService addBotService;

    @PostMapping("/api/user/bot/add/")
    public Map<String, String> addBot(@RequestParam Map<String, String> data) {
        return addBotService.addBot(data);
    }

}
