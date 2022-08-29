package com.kob.matchingsystem.service.impl.utils;

public class Player {
    private Integer userId;
    private Integer rating;
    private Integer waitingTime; //等待时间

    public Player() {
    }

    public Player(Integer userId, Integer rating, Integer waitingTime) {
        this.userId = userId;
        this.rating = rating;
        this.waitingTime = waitingTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "Player{" +
                "userId=" + userId +
                ", rating=" + rating +
                ", waitingTime=" + waitingTime +
                '}';
    }
}
