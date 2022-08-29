package com.kob.backend.consumer.utils;

public class Cell {
    public Integer x;
    public Integer y;

    public Cell() {
    }

    public Cell(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
