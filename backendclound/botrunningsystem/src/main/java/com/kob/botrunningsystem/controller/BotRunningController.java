package com.kob.botrunningsystem.controller;

import com.kob.botrunningsystem.service.BotRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class BotRunningController {
    @Autowired
    private BotRunningService botRunningService;

    @PostMapping("/bot/add/")
    public String addBot(@RequestParam MultiValueMap<String, String> map) {
        Integer userId = Integer.valueOf(Objects.requireNonNull(map.getFirst("user_id")));
        String botCode = map.getFirst("bot_code");
        String input = map.getFirst("input");
        return botRunningService.addBot(userId, botCode, input);
    }
}
