package com.company;

import java.util.ArrayList;

public class Order {
    private String name;
    private ArrayList<String> list = new ArrayList<>();

    Order(String name, ArrayList list) {
        this.name = name;
        this.list.addAll(list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
}
