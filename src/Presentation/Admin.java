package Presentation;

import Domain.Driver;
import Domain.Order;
import Domain.Partner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Application.*;

import java.util.ArrayList;


public class Admin extends Application {

    AnchorPane root;
    AnchorPane contentMenu;
    AnchorPane showLaundry;
    AnchorPane createLaundry;
    private AnchorPane showPartner;
    AnchorPane createPartner;
    AnchorPane showDriver;
    AnchorPane createDriver;
    AnchorPane showOrder;

    Button backToMenu;

    Button laundry;
    Button drivers;
    Button partner;
    Button order;
    Stage primaryStage;

    Scene scene;
    private int sceneWidth = 1200;
    private int sceneHeight = 1000;
    private ObservableList<Partner> partnerArrayList;
    public ArrayList<Driver> driverArrayList;
    private ArrayList<Order> orderArrayList;
    private int size = 0;
    ControllerAdmin controller = new ControllerAdmin();





    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
        menu();
        //showLaundry();
        //createLaundry();
        //createButtons();

    }

    public void createButtons(){
        /*Button[] buttons = new Button[4];

        for (int i = 0; i <4 ; i++) {
            buttons[i] = new Button();
            buttons[i].setLayoutX(100);
            buttons[i].setLayoutY(i*140);
            buttons[i].setPrefWidth(110);
            buttons[i].setPrefHeight(110);

            root.getChildren().addAll(buttons[i]);
        }*/



    }

    public void menu(){
        contentMenu = new AnchorPane();
        contentMenu.setPrefWidth(sceneWidth);
        contentMenu.setPrefHeight(sceneHeight-80);
        contentMenu.setLayoutY(80);
        contentMenu.setStyle("-fx-background-color: red");

        laundry = new Button("Laundry");
        laundry.setLayoutX(300);
        laundry.setLayoutY(200);
        laundry.setPrefHeight(150);
        laundry.setPrefWidth(180);

        partner = new Button("Partner");
        partner.setLayoutX(550);
        partner.setLayoutY(200);
        partner.setPrefHeight(150);
        partner.setPrefWidth(180);

        drivers = new Button("Driver");
        drivers.setLayoutX(300);
        drivers.setLayoutY(410);
        drivers.setPrefHeight(150);
        drivers.setPrefWidth(180);

        order = new Button("Order");
        order.setLayoutX(550);
        order.setLayoutY(410);
        order.setPrefHeight(150);
        order.setPrefWidth(180);

        laundry.setOnAction(e -> {
            showLaundry();
            createLaundry();
            contentMenu.setVisible(false);
            backToMenu();
        });

        partner.setOnAction(e -> {
            showPartner();
            createPartner();
            contentMenu.setVisible(false);
            backToMenu();
        });

        drivers.setOnAction(e -> {
            showDriver();
            createDriver();
            contentMenu.setVisible(false);
            backToMenu();
        });

        order.setOnAction(e -> {
            showOrder();
            contentMenu.setVisible(false);
            backToMenu();
        });

        contentMenu.getChildren().addAll(laundry,partner,drivers,order);
        root.getChildren().addAll(contentMenu);
    }

    public void backToMenu(){
        backToMenu = new Button("Back to Menu");
        backToMenu.setLayoutX(30);
        backToMenu.setLayoutY(15);
        backToMenu.setPrefWidth(120);
        backToMenu.setPrefHeight(50);

        backToMenu.setOnAction(e -> {

            menu();
            backToMenu.setVisible(false);

        });

        root.getChildren().addAll(backToMenu);

    }

    public void showLaundry(){
        showLaundry = new AnchorPane();
        showLaundry.setPrefWidth(600);
        showLaundry.setLayoutY(80);
        showLaundry.setPrefHeight(sceneHeight-80);
        showLaundry.setStyle("-fx-background-color: blue");

        Label laundyLabel = new Label("All Laundry");
        laundyLabel.setLayoutX(250);
        laundyLabel.setLayoutY(50);


        showLaundry.getChildren().addAll(laundyLabel);
        root.getChildren().addAll(showLaundry);
    }

    public void createLaundry(){
        createLaundry = new AnchorPane();
        createLaundry.setPrefWidth(600);
        createLaundry.setPrefHeight(sceneHeight-80);
        createLaundry.setLayoutY(80);
        createLaundry.setLayoutX(600);
        createLaundry.setStyle("-fx-background-color: orange");

        TextField laundryType = new TextField();
        laundryType.setPrefWidth(400);
        laundryType.setPrefHeight(70);
        laundryType.setLayoutX(100);
        laundryType.setLayoutY(200);
        laundryType.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        laundryType.setFocusTraversable(false);
        laundryType.setTooltip(new Tooltip("Laundry Type"));
        laundryType.setAlignment(Pos.CENTER);
        laundryType.setPromptText("Laundry Type");

        TextField laundryPrice = new TextField();
        laundryPrice.setPrefWidth(400);
        laundryPrice.setPrefHeight(70);
        laundryPrice.setLayoutX(100);
        laundryPrice.setLayoutY(300);
        laundryPrice.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        laundryPrice.setFocusTraversable(false);
        laundryPrice.setTooltip(new Tooltip("Price"));
        laundryPrice.setAlignment(Pos.CENTER);
        laundryPrice.setPromptText("Price");

        laundryPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    laundryPrice.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button submitLaundry = new Button("Create Laundry");
        submitLaundry.setPrefWidth(400);
        submitLaundry.setPrefHeight(70);
        submitLaundry.setLayoutX(100);
        submitLaundry.setLayoutY(400);

        createLaundry.getChildren().addAll(laundryType,laundryPrice,submitLaundry);
        root.getChildren().addAll(createLaundry);
    }

    public void showPartner1(){
        showPartner = new AnchorPane();
        showPartner.setPrefWidth(600);
        showPartner.setLayoutY(80);
        showPartner.setPrefHeight(sceneHeight-80);
        showPartner.setStyle("-fx-background-color: blue");


        root.getChildren().addAll(showPartner);


    }


    public void showPartner() {
        showPartner = new AnchorPane();
        //showPartnerVBox.setPadding(new Insets(100,10,0,10));
        showPartner.setPrefWidth(600);
        showPartner.setLayoutY(80);
        showPartner.setPrefHeight(sceneHeight-80);
        showPartner.setStyle("-fx-background-color: blue");
        root.getChildren().addAll(showPartner);
        controller.getPartners();
        partnerArrayList = controller.partnerArrayList;

        TableView tableView = new TableView();
        tableView.setEditable(true);
        tableView.setPrefHeight(800);
        tableView.setPrefWidth(600);

        TableColumn<String, Partner> columnPartnerName = new TableColumn<>("Partner Name");
        columnPartnerName.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<String, Partner> columnPartnerPhoneNo = new TableColumn<>("Partner PhoneNo");
        columnPartnerPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));


        tableView.getColumns().add(columnPartnerName);
        tableView.getColumns().add(columnPartnerPhoneNo);

        for (int i = 0; i < partnerArrayList.size() ; i++) {
            tableView.getItems().add(partnerArrayList.get(i));

        }



        showPartner.getChildren().add(tableView);


    }

    public void showPartnerWorking(){
        showPartner = new AnchorPane();
        //showPartnerVBox.setPadding(new Insets(100,10,0,10));
        showPartner.setPrefWidth(600);
        showPartner.setLayoutY(80);
        showPartner.setPrefHeight(sceneHeight-80);
        showPartner.setStyle("-fx-background-color: blue");
        root.getChildren().addAll(showPartner);
        controller.getPartners();
        partnerArrayList = controller.partnerArrayList;

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(showPartner);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(600, sceneHeight-80);
        scrollPane.setLayoutY(80);

        root.getChildren().add(scrollPane);


        for (int i = 0; i < partnerArrayList.size(); i++) {
            AnchorPane partnerPane = new AnchorPane();
            partnerPane.setPrefWidth(500);
            partnerPane.setPrefHeight(70);
            partnerPane.setLayoutX(40);
            partnerPane.setLayoutY(size+=100);
            partnerPane.setStyle("-fx-background-color: red");

            Label partnerNameLabel = new Label("Partner name: " +partnerArrayList.get(i).getName());
            partnerNameLabel.setLayoutX(10);
            partnerNameLabel.setLayoutY(25);
            partnerNameLabel.setTextFill(Color.BLACK);
            partnerNameLabel.setFont(new Font(16));

            /*Label partnerAddressLabel = new Label("Address: " + partnerArrayList.get(i).getAddress());
            partnerAddressLabel.setLayoutX(70);
            partnerAddressLabel.setLayoutY(25);
            partnerAddressLabel.setTextFill(Color.BLACK);
             */

            Label partnerPhoneNoLabel = new Label("PhoneNO: " + partnerArrayList.get(i).getPhoneNo());
            partnerPhoneNoLabel.setLayoutX(250);
            partnerPhoneNoLabel.setLayoutY(25);
            partnerPhoneNoLabel.setTextFill(Color.BLACK);

           /* Label partnerEmailLabel = new Label("Email: " + partnerArrayList.get(i).getEmail());
            partnerEmailLabel.setLayoutX(250);
            partnerEmailLabel.setLayoutY(25);
            partnerEmailLabel.setTextFill(Color.BLACK);

            */

            partnerPane.getChildren().addAll(partnerNameLabel, partnerPhoneNoLabel);
            showPartner.getChildren().addAll(partnerPane);



        }
        size = 0;
    }

    public void createPartner(){
        createPartner = new AnchorPane();
        createPartner.setPrefWidth(600);
        createPartner.setPrefHeight(sceneHeight-80);
        createPartner.setLayoutY(80);
        createPartner.setLayoutX(600);
        createPartner.setStyle("-fx-background-color: orange");

        TextField partnerName = new TextField();
        partnerName.setPrefWidth(400);
        partnerName.setPrefHeight(70);
        partnerName.setLayoutX(100);
        partnerName.setLayoutY(100);
        partnerName.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerName.setFocusTraversable(false);
        partnerName.setTooltip(new Tooltip("Partner Name"));
        partnerName.setAlignment(Pos.CENTER);
        partnerName.setPromptText("Partner Name");

        TextField partnerEmail = new TextField();
        partnerEmail.setPrefWidth(400);
        partnerEmail.setPrefHeight(70);
        partnerEmail.setLayoutX(100);
        partnerEmail.setLayoutY(200);
        partnerEmail.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerEmail.setFocusTraversable(false);
        partnerEmail.setTooltip(new Tooltip("Partner Email"));
        partnerEmail.setAlignment(Pos.CENTER);
        partnerEmail.setPromptText("Partner Email");

        TextField partnerAddress = new TextField();
        partnerAddress.setPrefWidth(400);
        partnerAddress.setPrefHeight(70);
        partnerAddress.setLayoutX(100);
        partnerAddress.setLayoutY(300);
        partnerAddress.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerAddress.setFocusTraversable(false);
        partnerAddress.setTooltip(new Tooltip("Partner Address"));
        partnerAddress.setAlignment(Pos.CENTER);
        partnerAddress.setPromptText("Partner Address");

        TextField partnerPhone = new TextField();
        partnerPhone.setPrefWidth(400);
        partnerPhone.setPrefHeight(70);
        partnerPhone.setLayoutX(100);
        partnerPhone.setLayoutY(400);
        partnerPhone.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerPhone.setFocusTraversable(false);
        partnerPhone.setTooltip(new Tooltip("Partner Phone Number"));
        partnerPhone.setAlignment(Pos.CENTER);
        partnerPhone.setPromptText("Partner Phone Number");

        TextField partnerCode = new TextField();
        partnerCode.setPrefWidth(400);
        partnerCode.setPrefHeight(70);
        partnerCode.setLayoutX(100);
        partnerCode.setLayoutY(500);
        partnerCode.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerCode.setFocusTraversable(false);
        partnerCode.setTooltip(new Tooltip("Partner Code"));
        partnerCode.setAlignment(Pos.CENTER);
        partnerCode.setPromptText("Partner code");

        PasswordField partnerPassword = new PasswordField();
        partnerPassword.setPrefWidth(400);
        partnerPassword.setPrefHeight(70);
        partnerPassword.setLayoutX(100);
        partnerPassword.setLayoutY(600);
        partnerPassword.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerPassword.setFocusTraversable(false);
        partnerPassword.setAlignment(Pos.CENTER);
        partnerPassword.setPromptText("Partner Password");

        TextField partnerZipCity = new TextField();
        partnerZipCity.setPrefWidth(400);
        partnerZipCity.setPrefHeight(70);
        partnerZipCity.setLayoutX(100);
        partnerZipCity.setLayoutY(700);
        partnerZipCity.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerZipCity.setFocusTraversable(false);
        partnerZipCity.setTooltip(new Tooltip("Partner Zip Code"));
        partnerZipCity.setAlignment(Pos.CENTER);
        partnerZipCity.setPromptText("Partner Zip Code");

        Button submitPartner = new Button("Create Partner");
        submitPartner.setPrefWidth(400);
        submitPartner.setPrefHeight(70);
        submitPartner.setLayoutX(100);
        submitPartner.setLayoutY(800);
        submitPartner.setOnAction(event -> controller.createPartner(partnerName.getText(), partnerEmail.getText(), partnerAddress.getText(), partnerPhone.getText(),partnerCode.getText(), partnerPassword.getText(), partnerZipCity.getText()));
        createPartner.getChildren().addAll(partnerName,partnerEmail,partnerAddress,partnerPhone, partnerCode, partnerPassword, partnerZipCity, submitPartner);
        root.getChildren().addAll(createPartner);
    }


    public void showDriver(){
        showDriver = new AnchorPane();
        showDriver.setPrefWidth(600);
        showDriver.setLayoutY(80);
        showDriver.setPrefHeight(sceneHeight-80);
        showDriver.setStyle("-fx-background-color: blue");
        controller.getDrivers();
        driverArrayList = controller.driverArrayList;

        root.getChildren().addAll(showDriver);

        for (int i = 0; i < driverArrayList.size(); i++) {
            AnchorPane driverPane = new AnchorPane();
            driverPane.setPrefWidth(500);
            driverPane.setPrefHeight(70);
            driverPane.setLayoutX(40);
            driverPane.setLayoutY(size+=100);
            driverPane.setStyle("-fx-background-color: red");

            Label driverNameLabel = new Label("Partner name: " + driverArrayList.get(i).getName());
            driverNameLabel.setLayoutX(10);
            driverNameLabel.setLayoutY(25);
            driverNameLabel.setTextFill(Color.BLACK);
            driverNameLabel.setFont(new Font(16));


            Label driverPhoneNoLabel = new Label("PhoneNO: " + driverArrayList.get(i).getPhoneNo());
            driverPhoneNoLabel.setLayoutX(250);
            driverPhoneNoLabel.setLayoutY(25);
            driverPhoneNoLabel.setTextFill(Color.BLACK);

            driverPane.getChildren().addAll(driverNameLabel, driverPhoneNoLabel);
            showDriver.getChildren().addAll(driverPane);



        }
        size = 0;
    }

    public void createDriver(){
        createDriver = new AnchorPane();
        createDriver.setPrefWidth(600);
        createDriver.setPrefHeight(sceneHeight-80);
        createDriver.setLayoutY(80);
        createDriver.setLayoutX(600);
        createDriver.setStyle("-fx-background-color: orange");

        TextField driverName = new TextField();
        driverName.setPrefWidth(400);
        driverName.setPrefHeight(70);
        driverName.setLayoutX(100);
        driverName.setLayoutY(100);
        driverName.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        driverName.setFocusTraversable(false);
        driverName.setTooltip(new Tooltip("Driver Name"));
        driverName.setAlignment(Pos.CENTER);
        driverName.setPromptText("Driver Name");

        TextField driverPhone = new TextField();
        driverPhone.setPrefWidth(400);
        driverPhone.setPrefHeight(70);
        driverPhone.setLayoutX(100);
        driverPhone.setLayoutY(200);
        driverPhone.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        driverPhone.setFocusTraversable(false);
        driverPhone.setTooltip(new Tooltip("Driver Phone Number"));
        driverPhone.setAlignment(Pos.CENTER);
        driverPhone.setPromptText("Driver Phone Number");

        TextField driverEmail = new TextField();
        driverEmail.setPrefWidth(400);
        driverEmail.setPrefHeight(70);
        driverEmail.setLayoutX(100);
        driverEmail.setLayoutY(300);
        driverEmail.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        driverEmail.setFocusTraversable(false);
        driverEmail.setTooltip(new Tooltip("Driver Email"));
        driverEmail.setAlignment(Pos.CENTER);
        driverEmail.setPromptText("Driver Email");

        PasswordField driverPassword = new PasswordField();
        driverPassword.setPrefWidth(400);
        driverPassword.setPrefHeight(70);
        driverPassword.setLayoutX(100);
        driverPassword.setLayoutY(400);
        driverPassword.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        driverPassword.setFocusTraversable(false);
        driverPassword.setTooltip(new Tooltip("Driver Password"));
        driverPassword.setAlignment(Pos.CENTER);
        driverPassword.setPromptText("Password");

        Button submitDriver = new Button("Create Driver");
        submitDriver.setPrefWidth(400);
        submitDriver.setPrefHeight(70);
        submitDriver.setLayoutX(100);
        submitDriver.setLayoutY(500);

        createDriver.getChildren().addAll(driverName,driverPhone,driverEmail,driverPassword,submitDriver);
        root.getChildren().addAll(createDriver);
    }

    public void showOrder(){
        showOrder = new AnchorPane();
        showOrder.setPrefWidth(sceneWidth);
        showOrder.setLayoutY(80);
        showOrder.setPrefHeight(sceneHeight-80);
        showOrder.setStyle("-fx-background-color: blue");

        root.getChildren().addAll(showOrder);
        controller.getOrder();
        orderArrayList = controller.orderInfo;
        System.out.println(orderArrayList);

        Label citypPlaceholder;

        for (int i = 0; i < orderArrayList.size(); i++) {

            AnchorPane orderPane = new AnchorPane();
            orderPane.setPrefWidth(1120);
            orderPane.setPrefHeight(70);
            orderPane.setLayoutX(40);
            orderPane.setLayoutY(size += 100);
            orderPane.setStyle("-fx-background-color: red");

            Label namePlaceholder = new Label("Partner name: " + orderArrayList.get(i).getPartnerName());
            namePlaceholder.setLayoutX(25);
            namePlaceholder.setLayoutY(25);
            namePlaceholder.setTextFill(Color.BLACK);
            namePlaceholder.setFont(new Font(16));

            citypPlaceholder = new Label("City: " + orderArrayList.get(i).getCity());
            citypPlaceholder.setLayoutX(320);
            citypPlaceholder.setLayoutY(25);
            citypPlaceholder.setTextFill(Color.BLACK);

            Label addressPlaceholder = new Label("Address: " + orderArrayList.get(i).getAddress());
            addressPlaceholder.setLayoutX(490);
            addressPlaceholder.setLayoutY(25);
            addressPlaceholder.setTextFill(Color.BLACK);

            Label orderNumberPlaceholder = new Label("Order Number: " + orderArrayList.get(i).getOrderNumber());
            orderNumberPlaceholder.setLayoutX(700);
            orderNumberPlaceholder.setLayoutY(25);
            orderNumberPlaceholder.setTextFill(Color.BLACK);

            Label orderDatePlaceholder = new Label("Date: " + orderArrayList.get(i).getOrderDate());
            orderDatePlaceholder.setLayoutX(960);
            orderDatePlaceholder.setLayoutY(25);
            orderDatePlaceholder.setTextFill(Color.BLACK);


            showOrder.getChildren().addAll(orderPane);
            orderPane.getChildren().addAll(namePlaceholder,citypPlaceholder,addressPlaceholder, orderNumberPlaceholder,orderDatePlaceholder);
        }
        size = 0;
    }

    public void sceneRoot(){
        primaryStage = new Stage();

        root = new AnchorPane();
        root.setStyle("-fx-background-color: #b8cfcc");


        scene = new Scene(root,sceneWidth,sceneHeight);

        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showAdmin() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
