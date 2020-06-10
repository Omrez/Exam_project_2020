package Presentation;
import Application.*;
import Domain.Order;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DriverOrder extends Application {
    Stage primaryStage;
    Group root;
    AnchorPane menu;
    AnchorPane showPartnerOrder;
    AnchorPane showCentralOrder;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    ArrayList<Order> orderArray;
    int size = 0;

    Button partnerOrders;
    Button centralOrder;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
       // menu();
        orderDetails();
        //showPartnerOrder();
        //showCentralOrder();


    }

    public void sceneRoot(){
        primaryStage = new Stage();

        root = new Group();
        Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));
        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void menu(){
        menu = new AnchorPane();
        menu.setPrefWidth(sceneWidth);
        menu.setPrefHeight(80);
        menu.setStyle("-fx-background-color: red");

        partnerOrders = new Button("Partner Order");
        partnerOrders.setLayoutX(30);
        partnerOrders.setLayoutY(15);
        partnerOrders.setPrefHeight(50);
        partnerOrders.setPrefWidth(120);

        centralOrder = new Button("Central Order");
        centralOrder.setLayoutX(180);
        centralOrder.setLayoutY(15);
        centralOrder.setPrefHeight(50);
        centralOrder.setPrefWidth(120);

        partnerOrders.setOnAction(e -> {

            orderDetails();

        });

        centralOrder.setOnAction(e -> {

            centralDetails();

        });


        menu.getChildren().addAll(partnerOrders,centralOrder);
        root.getChildren().addAll(menu);
    }


    public void orderDetails(){

        ControllerDriver controller = new ControllerDriver();
        controller.getOrder();
        orderArray = controller.orderInfo;
        System.out.println(orderArray);


        Label citypPlaceholder;

        for (int i = 0; i < orderArray.size(); i++) {

            AnchorPane orderPane = new AnchorPane();
            orderPane.setPrefWidth(1120);
            orderPane.setPrefHeight(70);
            orderPane.setLayoutX(40);
            orderPane.setLayoutY(size += 100);
            orderPane.setStyle("-fx-background-color: red");

            Label namePlaceholder = new Label("Partner name: " + orderArray.get(i).getPartnerName());
            namePlaceholder.setLayoutX(25);
            namePlaceholder.setLayoutY(25);
            namePlaceholder.setTextFill(Color.BLACK);
            namePlaceholder.setFont(new Font(16));

            citypPlaceholder = new Label("City: " + orderArray.get(i).getCity());
            citypPlaceholder.setLayoutX(320);
            citypPlaceholder.setLayoutY(25);
            citypPlaceholder.setTextFill(Color.BLACK);

            Label addressPlaceholder = new Label("Address: " + orderArray.get(i).getAddress());
            addressPlaceholder.setLayoutX(490);
            addressPlaceholder.setLayoutY(25);
            addressPlaceholder.setTextFill(Color.BLACK);

            Label orderNumberPlaceholder = new Label("Order Number: " + orderArray.get(i).getOrderNumber());
            orderNumberPlaceholder.setLayoutX(700);
            orderNumberPlaceholder.setLayoutY(25);
            orderNumberPlaceholder.setTextFill(Color.BLACK);

            Label orderDatePlaceholder = new Label("Date: " + orderArray.get(i).getOrderDate());
            orderDatePlaceholder.setLayoutX(960);
            orderDatePlaceholder.setLayoutY(25);
            orderDatePlaceholder.setTextFill(Color.BLACK);


            root.getChildren().addAll(orderPane);
            orderPane.getChildren().addAll(namePlaceholder,citypPlaceholder,addressPlaceholder, orderNumberPlaceholder,orderDatePlaceholder);
        }

    }

    public void centralDetails(){

        Label lb1 = new Label("Label 1");
        lb1.setLayoutX(200);
        lb1.setLayoutY(200);

        Label lb2 = new Label("Label 2");
        lb2.setLayoutX(200);
        lb2.setLayoutY(300);

        Label lb3 = new Label("Label 3");
        lb3.setLayoutX(200);
        lb3.setLayoutY(400);

        root.getChildren().addAll(lb1,lb2,lb3);
    }

    public void showDriver() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
