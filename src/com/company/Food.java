package com.company;

public class Food {
    private String name;
    private int massa;
    private int cost;

    Food(String name, int massa, int cost) {
        this.name = name;
        this.massa = massa;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMassa() {
        return massa;
    }

    public void setMassa(int massa) {
        this.massa = massa;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
