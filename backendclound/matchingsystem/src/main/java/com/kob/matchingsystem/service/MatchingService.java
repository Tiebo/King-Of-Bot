package com.kob.matchingsystem.service;

public interface MatchingService {
    String addPlayer(Integer id, Integer rating, Integer botId);
    String removePlayer(Integer userId);
}
