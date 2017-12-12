package com.company;

public class Food implements Print {
    private String name;
    private int massa;
    private int cost;
    private static final String GRAM = " грамм ";
    private static final String RUB = " руб. ";

    Food(String name, int massa, int cost) {
        this.name = name;
        this.massa = massa;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public void printInfo() {
        System.out.println("*  " + name + " " + massa + GRAM + cost + RUB);
    }
}
