package com.kob.backend.controller.user.bot.getlist;

import com.kob.backend.service.user.bot.getlist.getListBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class getlistBotController {
    @Autowired
    private getListBotService getListBotService;

    @GetMapping("/api/user/bot/getlist/")
    public Map<String, Object> getList() {
        return getListBotService.getList();
    }
}
