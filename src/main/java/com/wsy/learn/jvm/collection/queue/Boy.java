package com.wsy.learn.jvm.collection.queue;


public class Boy {
    private int house;
    private int car;
    private int single;

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "house=" + house +
                ", car=" + car +
                ", single=" + single +
                '}';
    }
}
