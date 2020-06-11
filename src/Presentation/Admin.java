package Presentation;

import Domain.Clothing;
import Domain.Driver;
import Domain.Order;
import Domain.Partner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Application.*;

import java.util.ArrayList;


public class Admin extends Application {

    private AnchorPane root;
    private AnchorPane contentMenu;
    private AnchorPane showLaundry;
    private AnchorPane createLaundry;
    private AnchorPane showPartner;
    private AnchorPane createPartner;
    private AnchorPane showDriver;
    private AnchorPane createDriver;
    private AnchorPane showOrder;

    private Button backToMenu;

    private Button laundry;
    private Button drivers;
    private Button partner;
    private Button order;
    public Stage primaryStage;

    private TextField partnerEmail;
    private TextField partnerAddress;
    private TextField partnerPhone;
    private TextField partnerCode;
    private TextField partnerZipCity;
    private PasswordField partnerPassword;
    private Button submitPartner;
    private TableView tableViewPartner = new TableView();
    private TableView tableViewDriver = new TableView();
    private TableView tableViewClothing = new TableView();

    private Scene scene;
    private int sceneWidth = 1200;
    private int sceneHeight = 1000;
    private ArrayList<Partner> partnerArrayList;
    public ArrayList<Driver> driverArrayList;
    private ArrayList<Order> orderArrayList;
    private ArrayList<Clothing> clothingArrayList;
    private int size = 0;
    private TextField partnerName;

    ControllerAdmin controller = new ControllerAdmin();


    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
        menu();
    }

    /**
     * sceneRoot is the root where the stage and scene is set and shown.
     */
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

    /**
     * showAdmin is used to load/open the Admin UI from another class.
     */
    public void showAdmin() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The menu method is used to create the UI Buttons and set Button actions.
     */
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

    /**
     * showLaundry method generates the UI and TableView for getting and updating laundry info.
     */
    public void showLaundry(){
        showLaundry = new AnchorPane();
        showLaundry.setPrefWidth(600);
        showLaundry.setLayoutY(80);
        showLaundry.setPrefHeight(sceneHeight-80);
        showLaundry.setStyle("-fx-background-color: blue");
        root.getChildren().addAll(showLaundry);
        controller.getClothing();
        clothingArrayList = controller.clothingArrayList;
        createTableViewClothing();
    }

    /**
     * This method below creates a tableview and fills it with data from the Database.
     */

    public void createTableViewClothing() {
        tableViewClothing.setEditable(true);
        tableViewClothing.setPrefHeight(800);
        tableViewClothing.setPrefWidth(600);

        TableColumn<Clothing, String> columnClothingType = new TableColumn<>("Clothing type");
        columnClothingType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnClothingType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnClothingType.setOnEditCommit(event -> {
            Clothing clothing = event.getRowValue();
            clothing.setType(event.getNewValue());
            controller.updateDBClothingType(clothing.getClothingID(), clothing.getType());

        });

        TableColumn<Clothing, String> columnClothingPrice = new TableColumn<>("Price");
        columnClothingPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnClothingPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        columnClothingPrice.setOnEditCommit(event -> {
            Clothing clothing= event.getRowValue();
            clothing.setPrice(event.getNewValue());
            controller.updateDBClothingPrice(clothing.getClothingID(), String.valueOf(clothing.getPrice()));
        });

        //This if else statement below checks if tableViewClothing is already created.
        if (!tableViewClothing.getColumns().isEmpty()) {

        } else  {
            tableViewClothing.getColumns().add(columnClothingType);
            tableViewClothing.getColumns().add(columnClothingPrice);

            for (int i = 0; i < clothingArrayList.size() ; i++) {
                tableViewClothing.getItems().add(clothingArrayList.get(i));

            }
        }
        showLaundry.getChildren().add(tableViewClothing);

    }

    /**
     * createLaundry method creates the UI for creating new laundry types and prices in the system.
     */
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

    /**
     * showPartner method generates the UI and TableView for getting and updating partner info.
     */
    public void showPartner() {
        showPartner = new AnchorPane();
        showPartner.setPrefWidth(600);
        showPartner.setLayoutY(80);
        showPartner.setPrefHeight(sceneHeight-80);
        showPartner.setStyle("-fx-background-color: blue");
        root.getChildren().addAll(showPartner);
        controller.getPartners();
        partnerArrayList = controller.partnerArrayList;
        createTableViewPartner();
    }

    /**
     * This method is run when the submitPartner button is clicked. It takes all info from the Fields provided
     * by the Admin, and inserts it into the database. It also clears the Fields in the UI for easy typing of a new partner.
     */
    public void createPartnerDB() {

        controller.createPartner(partnerName.getText(), partnerEmail.getText(), partnerAddress.getText(), partnerPhone.getText(),partnerCode.getText(), partnerPassword.getText(), partnerZipCity.getText());
        controller.createUserAccountPartner(partnerEmail.getText(), partnerPassword.getText());
        partnerArrayList.clear();
        controller.getPartners();
        partnerArrayList = controller.partnerArrayList;

        partnerPhone.clear();
        partnerAddress.clear();
        partnerName.clear();
        partnerPassword.clear();
        partnerCode.clear();
        partnerZipCity.clear();
        partnerEmail.clear();
    }

    /**
     * This method below creates a tableview and fills it with data from the Database.
     */
    public void createTableViewPartner() {

        tableViewPartner.setEditable(true);
        tableViewPartner.setPrefHeight(800);
        tableViewPartner.setPrefWidth(600);

        TableColumn<Partner, String> columnPartnerName = new TableColumn<>("Partner Name");
        columnPartnerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPartnerName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPartnerName.setOnEditCommit(event -> {
            Partner partner = event.getRowValue();
            partner.setName(event.getNewValue());
            controller.updateDBPartnerName(partner.getPartnerID(), partner.getName());
            System.out.println(partner.getName());
            System.out.println(partner.getPartnerID());
        });

        TableColumn<Partner, String> columnPartnerPhoneNo = new TableColumn<>("Partner PhoneNo");
        columnPartnerPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        columnPartnerPhoneNo.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPartnerPhoneNo.setOnEditCommit(event -> {
            Partner partner = event.getRowValue();
            partner.setPhoneNo(event.getNewValue());
            controller.updateDBPartnerPhoneNo(partner.getPartnerID(), partner.getPhoneNo());
            System.out.println(partner.getPartnerID());
        });

        TableColumn<Partner, String> columnPartnerEmail = new TableColumn<>("Email Address");
        columnPartnerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPartnerEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPartnerEmail.setOnEditCommit(event -> {
            Partner partner = event.getRowValue();
            partner.setEmail(event.getNewValue());
            controller.updateDBPartnerEmail(partner.getPartnerID(), partner.getEmail());
            System.out.println(partner.getPartnerID());
        });

        TableColumn<Partner, String> columnPartnerAddress = new TableColumn<>("Address");
        columnPartnerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnPartnerAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPartnerAddress.setOnEditCommit(event -> {
            Partner partner = event.getRowValue();
            partner.setAddress(event.getNewValue());
            controller.updateDBPartnerAddress(partner.getPartnerID(), partner.getAddress());
            System.out.println(partner.getPartnerID());
        });

        if (!tableViewPartner.getColumns().isEmpty()) {

        } else  {
            tableViewPartner.getColumns().add(columnPartnerName);
            tableViewPartner.getColumns().add(columnPartnerPhoneNo);
            tableViewPartner.getColumns().add(columnPartnerEmail);
            tableViewPartner.getColumns().add(columnPartnerAddress);


            for (int i = 0; i < partnerArrayList.size() ; i++) {
                tableViewPartner.getItems().add(partnerArrayList.get(i));

            }

        }
        showPartner.getChildren().add(tableViewPartner);

    }

    /**
     * This method below creates a tableview and fills it with data from the Database.
     */
    public void createTableViewDriver() {
        tableViewDriver.setEditable(true);
        tableViewDriver.setPrefHeight(800);
        tableViewDriver.setPrefWidth(600);

        TableColumn<Driver, String> columnDriverName = new TableColumn<>("Driver Name");
        columnDriverName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDriverName.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnPartnerName.setOnEditCommit(event -> updateCellInDB(TableColumn.CellEditEvent.ANY);
        columnDriverName.setOnEditCommit(event -> {
            Driver driver = event.getRowValue();
            driver.setName(event.getNewValue());
            controller.updateDBDriverName(driver.getDriverID(), driver.getName());
        });



        TableColumn<Driver, String> columnDriverPhoneNo = new TableColumn<>("Driver PhoneNo");
        columnDriverPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        columnDriverPhoneNo.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDriverPhoneNo.setOnEditCommit(event -> {
            Driver driver = event.getRowValue();
            driver.setPhoneNo(event.getNewValue());
            controller.updateDBDriverPhoneNo(driver.getDriverID(), driver.getPhoneNo());
        });

        TableColumn<Driver, String> columnDriverEmail = new TableColumn<>("Email Address");
        columnDriverEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnDriverEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDriverEmail.setOnEditCommit(event -> {
            Driver driver = event.getRowValue();
            driver.setEmail(event.getNewValue());
            controller.updateDBDriverEmail(driver.getDriverID(), driver.getEmail());
        });


        if (!tableViewDriver.getColumns().isEmpty()) {

        } else  {
            tableViewDriver.getColumns().add(columnDriverName);
            tableViewDriver.getColumns().add(columnDriverPhoneNo);
            tableViewDriver.getColumns().add(columnDriverEmail);


            for (int i = 0; i < driverArrayList.size() ; i++) {
                tableViewDriver.getItems().add(driverArrayList.get(i));

            }

        }
        showDriver.getChildren().add(tableViewDriver);

    }

    /**
     * The method createPartner creates the UI for creating a new partner, Fields and buttons.
     */
    public void createPartner(){
        createPartner = new AnchorPane();
        createPartner.setPrefWidth(600);
        createPartner.setPrefHeight(sceneHeight-80);
        createPartner.setLayoutY(80);
        createPartner.setLayoutX(600);
        createPartner.setStyle("-fx-background-color: orange");

        partnerName = new TextField();
        partnerName.setPrefWidth(400);
        partnerName.setPrefHeight(70);
        partnerName.setLayoutX(100);
        partnerName.setLayoutY(100);
        partnerName.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerName.setFocusTraversable(false);
        partnerName.setTooltip(new Tooltip("Partner Name"));
        partnerName.setAlignment(Pos.CENTER);
        partnerName.setPromptText("Partner Name");


        partnerEmail = new TextField();
        partnerEmail.setPrefHeight(70);
        partnerEmail.setPrefWidth(400);
        partnerEmail.setLayoutX(100);
        partnerEmail.setLayoutY(200);
        partnerEmail.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerEmail.setFocusTraversable(false);
        partnerEmail.setTooltip(new Tooltip("Partner Email"));
        partnerEmail.setAlignment(Pos.CENTER);
        partnerEmail.setPromptText("Partner Email");


        partnerAddress = new TextField();
        partnerAddress.setPrefWidth(400);
        partnerAddress.setPrefHeight(70);
        partnerAddress.setLayoutX(100);
        partnerAddress.setLayoutY(300);
        partnerAddress.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerAddress.setFocusTraversable(false);
        partnerAddress.setTooltip(new Tooltip("Partner Address"));
        partnerAddress.setAlignment(Pos.CENTER);
        partnerAddress.setPromptText("Partner Address");


        partnerPhone = new TextField();
        partnerPhone.setPrefWidth(400);
        partnerPhone.setPrefHeight(70);
        partnerPhone.setLayoutX(100);
        partnerPhone.setLayoutY(400);
        partnerPhone.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerPhone.setFocusTraversable(false);
        partnerPhone.setTooltip(new Tooltip("Partner Phone Number"));
        partnerPhone.setAlignment(Pos.CENTER);
        partnerPhone.setPromptText("Partner Phone Number");


        partnerCode = new TextField();
        partnerCode.setPrefWidth(400);
        partnerCode.setPrefHeight(70);
        partnerCode.setLayoutX(100);
        partnerCode.setLayoutY(500);
        partnerCode.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerCode.setFocusTraversable(false);
        partnerCode.setTooltip(new Tooltip("Partner Code"));
        partnerCode.setAlignment(Pos.CENTER);
        partnerCode.setPromptText("Partner code");


        partnerPassword = new PasswordField();
        partnerPassword.setPrefWidth(400);
        partnerPassword.setPrefHeight(70);
        partnerPassword.setLayoutX(100);
        partnerPassword.setLayoutY(600);
        partnerPassword.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerPassword.setFocusTraversable(false);
        partnerPassword.setAlignment(Pos.CENTER);
        partnerPassword.setPromptText("Partner Password");


        partnerZipCity = new TextField();
        partnerZipCity.setPrefWidth(400);
        partnerZipCity.setPrefHeight(70);
        partnerZipCity.setLayoutX(100);
        partnerZipCity.setLayoutY(700);
        partnerZipCity.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        partnerZipCity.setFocusTraversable(false);
        partnerZipCity.setTooltip(new Tooltip("Partner Zip Code"));
        partnerZipCity.setAlignment(Pos.CENTER);
        partnerZipCity.setPromptText("Partner Zip Code");


        submitPartner = new Button("Create Partner");
        submitPartner.setPrefWidth(400);
        submitPartner.setPrefHeight(70);
        submitPartner.setLayoutX(100);
        submitPartner.setLayoutY(800);
        submitPartner.setOnAction(event -> createPartnerDB());
        createPartner.getChildren().addAll(partnerName,partnerEmail,partnerAddress,partnerPhone, partnerCode, partnerPassword, partnerZipCity, submitPartner);
        root.getChildren().addAll(createPartner);
    }

    /**
     * showDriver method generates the UI and TableView for getting and updating driver info.
     */
    public void showDriver(){
        showDriver = new AnchorPane();
        showDriver.setPrefWidth(600);
        showDriver.setLayoutY(80);
        showDriver.setPrefHeight(sceneHeight-80);
        showDriver.setStyle("-fx-background-color: blue");
        root.getChildren().addAll(showDriver);
        controller.getDrivers();
        driverArrayList = controller.driverArrayList;
        createTableViewDriver();
    }

    /**
     * The method createDriver creates the UI for creating a new driver, Fields and buttons.
     */
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

    /**
     * showOrder method generates the UI of orders. An easy way for the driver and admin to see orders created.
     */
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
}
