package com.company;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class ParsingOrders {
    private static final Logger LOGGER = Logger.getLogger(ParsingOrders.class.getSimpleName());
    private static final String NAME_NODE = "name";
    private static final String WEIGHT_NODE = "massa";
    private static final String COST_NODE = "cost";
    private static final String PATH_FILE_MENU = "menu.xml";


    public ArrayList<Food> menu(String file) {

        ArrayList<Food> listMenu = new ArrayList<>();

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

                            if (propertyFood.getNodeName() == NAME_NODE) {
                                name = propertyFood.getChildNodes().item(0).getTextContent().trim();
                            }
                            if (propertyFood.getNodeName() == WEIGHT_NODE) {
                                weight = propertyFood.getChildNodes().item(0).getTextContent().trim();
                            }
                            if (propertyFood.getNodeName() == COST_NODE) {
                                cost = propertyFood.getChildNodes().item(0).getTextContent().trim();
                                Food itFood = new Food(name, Integer.parseInt(weight), Integer.parseInt(cost));
                                listMenu.add(itFood);
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return listMenu;
    }

    public void orders(String file) {

        ArrayList<Food> list = new ArrayList<>();
        ArrayList<Food> listMenu = menu(PATH_FILE_MENU);

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
                            if (propertyEmployee.getNodeName() == NAME_NODE) {
                                name = propertyEmployee.getChildNodes().item(0).getTextContent().trim();
                            }

                        }

                        NodeList ordersEmployee = propertyEmployee.getChildNodes();

                        for (int k = 0; k < ordersEmployee.getLength(); k++) {

                            Node orderEmployee = ordersEmployee.item(k);

                            if (orderEmployee.getNodeType() != Node.TEXT_NODE) {
                                order = orderEmployee.getChildNodes().item(0).getTextContent().trim();
                                for (int f = 0; f < listMenu.size(); f++) {
                                    if (order.equals(listMenu.get(f).getName())) {
                                        list.add(listMenu.get(f));
                                    }
                                }

                            }
                        }
                    }
                    Order itOrder = new Order(name, list);
                    itOrder.printInfo();
                    list.clear();
                }
            }

        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        } catch (SAXException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
