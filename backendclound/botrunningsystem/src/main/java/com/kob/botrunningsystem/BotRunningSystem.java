package com.kob.botrunningsystem;

import com.kob.botrunningsystem.service.impl.BotRunningServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotRunningSystem {
    public static void main(String[] args) {
        BotRunningServiceImpl.BOT_POOL.start();
        SpringApplication.run(BotRunningSystem.class, args);
    }
}