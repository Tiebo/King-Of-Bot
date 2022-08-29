package com.kob.matchingsystem.service;

public interface MatchingService {
    String addPlayer(Integer id, Integer rating);
    String removePlayer(Integer userId);
}
