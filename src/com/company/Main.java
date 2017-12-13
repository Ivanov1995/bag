package com.company;

public class Main {

    private static final String PATH_FILE_ORDERS = "orders.xml";

    public static void main(String[] args) {
        ParsingOrders obj = new ParsingOrders();
        obj.parseOrders(PATH_FILE_ORDERS);
    }
}

