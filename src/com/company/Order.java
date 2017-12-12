package com.company;

import java.util.ArrayList;

public class Order implements Print {
    private static final String NAME_EMPLOYEE = "Сотрудник ";
    private static final String TOTAL_COST = "ИТОГ ";
    private static final String RUB = " руб. ";
    private String name;
    private int totalCost;
    private ArrayList<Food> list = new ArrayList<>();

    Order(String name, ArrayList list) {
        this.name = name;
        this.list.addAll(list);
    }

    @Override
    public void printInfo() {
        System.out.println(">>>>>" + "\n" + NAME_EMPLOYEE + "- " + name);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printInfo();
            totalCost += list.get(i).getCost();
            if (i == list.size() - 1) {
                System.out.println(TOTAL_COST + "= " + totalCost + RUB + "\n" + "\n" + ">>>>>" + "\n" + NAME_EMPLOYEE + "- " + name);
                for (int j = 0; j < list.size(); j++) {
                    System.out.println("*  " + list.get(j).getName());
                    if (j == list.size() - 1) {
                        System.out.println(TOTAL_COST + " = " + totalCost + RUB + "\n");
                    }
                }
            }
        }
    }
}
