package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());
    private final static int NAME_INDEX = 1;
    private final static int WEIGHT_INDEX = 3;
    private final static int COST_INDEX = 5;
    private final static String GRAM = "грамм";
    private final static String RUBLES = "руб.";
    private final static String RESULT = "ИТОГ";
    private final static String NAME_EMPLOYEE = "Сотрудник";
    private final static String PARSE_EXCEPTION = "Parse Exception";
    private final static String SAX_EXEPTION = "SAX Exception";
    private final static String IO_EXCEPTION = "IO EXCEPTION";
    private final static String PATH_FILE_FOOD_XML = "food.xml";
    private final static String PATH_FILE_EMPLOYEES_XML = "Employees.xml";

    public static ArrayList menu(String file) {
        ArrayList<Food> list = new ArrayList<>();
        String name = "";
        String weight = "";
        String cost = "";
        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = documentBuilder.parse(file);

            Node foods = document.getDocumentElement();

            NodeList listOfNodesFoods = foods.getChildNodes();

            for (int i = 0; i < listOfNodesFoods.getLength(); i++) {

                Node food = listOfNodesFoods.item(i);

                if (food.getNodeType() != Node.TEXT_NODE) {

                    NodeList listOfNodesPropertyFoods = food.getChildNodes();

                    for (int j = 0; j < listOfNodesPropertyFoods.getLength(); j++) {

                        Node propertyFood = listOfNodesPropertyFoods.item(j);

                        if (propertyFood.getNodeType() != Node.TEXT_NODE) {

                            if (j == NAME_INDEX) {
                                name = propertyFood.getChildNodes().item(0).getTextContent().trim();
                            }
                            if (j == WEIGHT_INDEX) {
                                weight = propertyFood.getChildNodes().item(0).getTextContent().trim();
                            }
                            if (j == COST_INDEX) {
                                cost = propertyFood.getChildNodes().item(0).getTextContent().trim();
                                Food itFood = new Food(name, Integer.parseInt(weight), Integer.parseInt(cost));
                                list.add(itFood);
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, PARSE_EXCEPTION, e);
        } catch (SAXException e) {
            log.log(Level.SEVERE, SAX_EXEPTION, e);
        } catch (IOException e) {
            log.log(Level.SEVERE, IO_EXCEPTION, e);
        }
        return list;
    }

    public static ArrayList orders(String file) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Order> listOrders = new ArrayList<>();
        String name = "";
        String order = "";

        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = documentBuilder.parse(file);

            Node employees = document.getDocumentElement();

            NodeList listOfNodesEmployees = employees.getChildNodes();

            for (int i = 0; i < listOfNodesEmployees.getLength(); i++) {

                Node employee = listOfNodesEmployees.item(i);

                if (employee.getNodeType() != Node.TEXT_NODE) {

                    NodeList listOfNodesEmployee = employee.getChildNodes();

                    for (int j = 0; j < listOfNodesEmployee.getLength(); j++) {

                        Node propertyEmployee = listOfNodesEmployee.item(j);

                        if (propertyEmployee.getNodeType() != Node.TEXT_NODE) {
                            if (j == NAME_INDEX) {
                                name = propertyEmployee.getChildNodes().item(0).getTextContent().trim().toString();
                            }

                        }

                        NodeList ordersEmployee = propertyEmployee.getChildNodes();

                        for (int t = 0; t < ordersEmployee.getLength(); t++) {

                            Node orderEmployee = ordersEmployee.item(t);

                            if (orderEmployee.getNodeType() != Node.TEXT_NODE) {
                                order = orderEmployee.getChildNodes().item(0).getTextContent().trim().toString();
                                list.add(order);

                            }
                        }
                    }
                    Order itOrder = new Order(name, list);
                    listOrders.add(itOrder);
                    list.clear();
                }
            }

        } catch (ParserConfigurationException e) {
            log.log(Level.SEVERE, PARSE_EXCEPTION, e);
        } catch (SAXException e) {
            log.log(Level.SEVERE, SAX_EXEPTION, e);
        } catch (IOException e) {
            log.log(Level.SEVERE, IO_EXCEPTION, e);
        }
        return listOrders;
    }

    public static void printReportForEmployee(ArrayList<Food> menu, ArrayList<Order> orders) {

        for (int i = 0; i < orders.size(); i++) {
            int cost = 0;
            for (int a = 0; a < orders.get(i).getList().size(); a++) {
                for (int j = 0; j < menu.size(); j++) {
                    if (orders.get(i).getList().get(a).equals(menu.get(j).getName())) {
                        System.out.println(menu.get(j).getName() + " " + menu.get(j).getMassa() + " " + GRAM + " " + menu.get(j).getCost() + " " + RUBLES);
                        cost += menu.get(j).getCost();
                        if (a == orders.get(i).getList().size() - 1) {
                            System.out.println(RESULT + " " + cost + " " + RUBLES + "\n");
                            cost = 0;
                        }
                    }
                }
            }
        }
    }

    public static void printReportForUser(ArrayList<Food> menu, ArrayList<Order> orders) {

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(NAME_EMPLOYEE + " " + orders.get(i).getName());
            int cost = 0;
            for (int a = 0; a < orders.get(i).getList().size(); a++) {
                for (int j = 0; j < menu.size(); j++) {
                    if (orders.get(i).getList().get(a).equals(menu.get(j).getName())) {
                        System.out.println(menu.get(j).getName());
                        cost += menu.get(j).getCost();
                        if (a == orders.get(i).getList().size() - 1) {
                            System.out.println(RESULT + " " + cost + " " + RUBLES + "\n");
                            cost = 0;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Food> menu = menu(PATH_FILE_FOOD_XML);
        ArrayList<Order> orders = orders(PATH_FILE_EMPLOYEES_XML);
        printReportForEmployee(menu, orders);
        printReportForUser(menu, orders);
    }
}

