package com.kob.backend.consumer.utils;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Integer id;
    private Integer botId; // -1 表示亲自出马， 否则为AI
    private String botCode;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;

    public Integer getBotId() {
        return botId;
    }

    public void setBotId(Integer botId) {
        this.botId = botId;
    }

    private boolean check_tail_increasing(int step) {
        //检验当前回合，蛇是否会变长
        if (step < 10) return true;
        return step % 3 == 1;
    }

    public String getStepString() {
        StringBuilder res = new StringBuilder();
        for (int d: steps) {
            res.append(d);
        }
        return res.toString();
    }
    public List<Cell> getCells() {
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0,-1};
        int x = sx, y = sy;
        res.add(new Cell(x, y));
        int step = 0;
        for (int d: steps) {
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!check_tail_increasing( ++ step)) {
                res.remove(0);
            }
        }
        return res;
    }


    public Player() {
    }


    public Player(Integer id,Integer botId,String botCode, Integer sx, Integer sy, List<Integer> steps) {
        this.id = id;
        this.sx = sx;
        this.botId = botId;
        this.botCode = botCode;
        this.sy = sy;
        this.steps = steps;
    }

    public String getBotCode() {
        return botCode;
    }

    public void setBotCode(String botCode) {
        this.botCode = botCode;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", sx=" + sx +
                ", sy=" + sy +
                ", steps=" + steps +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }

    public void setSy(Integer sy) {
        this.sy = sy;
    }

    public void setSteps(List<Integer> steps) {
        this.steps = steps;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSx() {
        return sx;
    }

    public Integer getSy() { return sy; }

    public List<Integer> getSteps() {
        return steps;
    }
}
