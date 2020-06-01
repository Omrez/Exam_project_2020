package Presentation;
import Application.*;
import Domain.Partner;
import Service.DB;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class DriverOrder extends Application {
    Stage primaryStage;
    Group root;
    AnchorPane menu;
    AnchorPane showPartnerOrder;
    AnchorPane showCentralOrder;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;

    Button partnerOrders;
    Button centralOrder;

    Label driverName;
    Label partnerName;
    Label namePlaceholder;
    Label partnerCity;
    Label partnerAddress;
    Label orderNumber;
    Label orderDate;

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

        DB db = new DB();
        db.getOrder("select * from tblOrder");
        System.out.println(db.getOrderNumber());


        AnchorPane[] orderPane = new AnchorPane[4];

        Label[] namePlaceholder = new Label[4];
        Label[] citypPlaceholder = new Label[4];
        Label[] addressPlaceholder = new Label[4];
        Label[] orderNumberPlaceholder = new Label[4];
        Label[] orderDatePlaceholder = new Label[4];

        Label[] partnerName = new Label[4];
        Label[] partnerCity = new Label[4];
        Label[] partnerAddress = new Label[4];
        Label[] partnerOrder = new Label[4];
        Label[] partnerOrderDate = new Label[4];

        for (int i = 0; i < 4; i++) {
            orderPane[i] = new AnchorPane();
            orderPane[i].setPrefWidth(1120);
            orderPane[i].setPrefHeight(70);
            orderPane[i].setLayoutX(40);
            orderPane[i].setLayoutY(i*100);
            orderPane[i].setStyle("-fx-background-color: red");

            namePlaceholder[i] = new Label("Partner name: ");
            namePlaceholder[i].setLayoutX(25);
            namePlaceholder[i].setLayoutY(25);
            namePlaceholder[i].setTextFill(Color.GRAY);
            namePlaceholder[i].setFont(new Font(16));

            citypPlaceholder[i] = new Label("City: ");
            citypPlaceholder[i].setLayoutX(320);
            citypPlaceholder[i].setLayoutY(25);
            citypPlaceholder[i].setTextFill(Color.GRAY);

            addressPlaceholder[i] = new Label("Address: ");
            addressPlaceholder[i].setLayoutX(490);
            addressPlaceholder[i].setLayoutY(25);
            addressPlaceholder[i].setTextFill(Color.GRAY);

            orderNumberPlaceholder[i] = new Label("Order Number: ");
            orderNumberPlaceholder[i].setLayoutX(700);
            orderNumberPlaceholder[i].setLayoutY(25);
            orderNumberPlaceholder[i].setTextFill(Color.GRAY);

            orderDatePlaceholder[i] = new Label("Date: ");
            orderDatePlaceholder[i].setLayoutX(960);
            orderDatePlaceholder[i].setLayoutY(25);
            orderDatePlaceholder[i].setTextFill(Color.GRAY);

            partnerName[i] = new Label("get partner name");
            partnerName[i].setLayoutX(135);
            partnerName[i].setLayoutY(25);

            partnerCity[i] = new Label("get city");
            partnerCity[i].setLayoutX(360);
            partnerCity[i].setLayoutY(25);

            partnerAddress[i] = new Label("get Address");
            partnerAddress[i].setLayoutX(560);
            partnerAddress[i].setLayoutY(25);

            partnerOrder[i] = new Label("get order");
            partnerOrder[i].setLayoutX(820);
            partnerOrder[i].setLayoutY(25);

            partnerOrderDate[i] = new Label("get Date");
            partnerOrderDate[i].setLayoutX(1010);
            partnerOrderDate[i].setLayoutY(25);


           // System.out.println(i+" = "+orderPane[i].getLayoutY());


            root.getChildren().addAll(orderPane[i]);
            orderPane[i].getChildren().addAll(partnerName[i], namePlaceholder[i],citypPlaceholder[i],partnerCity[i],addressPlaceholder[i],partnerAddress[i],orderNumberPlaceholder[i],partnerOrder[i],orderDatePlaceholder[i],partnerOrderDate[i]);
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



}
