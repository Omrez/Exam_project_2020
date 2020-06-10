package Presentation;

import Application.*;
import Domain.AutoInsertTextField;
import Domain.Clothing;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Register extends Application {
    Group root;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    Stage primaryStage;
    //private int count;
    private ArrayList<Clothing> clothingArrayList;
    private ArrayList<String> customerPhoneNo;
    TextField totalPrices = new TextField();

    TextField customerName;
    AutoInsertTextField customerPhone = new AutoInsertTextField();
    Button createOrderBtn;

    AnchorPane content;
    AnchorPane laundryInfo;

    Label totalPricePlaceholder;
    Label totalPrice;

    final ToggleGroup group = new ToggleGroup();
    RadioButton newCustomer;
    RadioButton existingCustomer;
    int priceOfJeans = 70;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();

        customerInfo();
        createOrderBtn();

        content();


    }

    public void sceneRoot(){
        primaryStage = new Stage();
        root = new Group();
        Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));
        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.show();

        totalPricePlaceholder = new Label("Total Price: ");
        totalPricePlaceholder.setLayoutX(950);
        totalPricePlaceholder.setLayoutY(300);
        totalPricePlaceholder.setFont(new Font(22));

        totalPrice = new Label("price");
        totalPrice.setLayoutX(998);
        totalPrice.setLayoutY(350);
        totalPrice.setFont(new Font(22));
        totalPrice.setTextFill(Color.CORAL);



        root.getChildren().addAll(totalPricePlaceholder,totalPrice);
    }

    public void customerFields(TextField customer, int customerX, int customerY, String customerText, String toolTip){
        //customer = new TextField();
        customer.setPrefWidth(480);
        customer.setPrefHeight(50);
        customer.setLayoutX(customerX);
        customer.setLayoutY(customerY);
        customer.setPromptText(customerText);
        customer.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        customer.setFocusTraversable(false);
        customer.setTooltip(new Tooltip(toolTip));
        customer.setAlignment(Pos.CENTER);

        root.getChildren().addAll(customer);
    }

    public void content(){
        content = new AnchorPane();
        content.setLayoutX(305);
        content.setLayoutY(180);
        content.setPrefWidth(480);
        content.setPrefHeight(430);
        //content.setStyle("-fx-background-color: blue;");
        ControllerPartner controller = new ControllerPartner();
        controller.getClothing();
        clothingArrayList = controller.clothingArrayList;

        ScrollPane sp = new ScrollPane();
        sp.setContent(content);
        sp.setLayoutX(305);
        sp.setLayoutY(180);
        sp.setPrefSize(480,430);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (int i = 0; i < clothingArrayList.size(); i++) {
            laundryInfo = new AnchorPane();
            laundryInfo.setPrefWidth(480);
            laundryInfo.setPrefHeight(80);
            laundryInfo.setLayoutY(i*100);
            laundryInfo.setStyle("-fx-background-color: red");

            ImageView logo = new ImageView("Presentation/images/" + clothingArrayList.get(i).getType() + ".png");
            logo.setLayoutY(5);

            Label laundryType = new Label(clothingArrayList.get(i).getType());
            laundryType.setLayoutX(80);
            laundryType.setLayoutY(25);

            TextField laundryQuantity = new TextField("0");
            laundryQuantity.setLayoutX(195);
            laundryQuantity.setLayoutY(20);
            laundryQuantity.setPrefWidth(80);
            laundryQuantity.setFocusTraversable(false);
            laundryQuantity.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
            laundryQuantity.setAlignment(Pos.CENTER);

            Clothing clothingObject = clothingArrayList.get(i);

            Button minus = new Button("-");
            minus.setLayoutX(150);
            minus.setLayoutY(20);
            minus.setPrefWidth(30);
            minus.setStyle("-fx-background-color: #88ff85");
            minus.setOnAction(event -> minusQuantity(laundryQuantity, clothingObject));

            Button plus = new Button("+");
            plus.setLayoutX(290);
            plus.setLayoutY(20);
            plus.setPrefWidth(35);
            plus.setStyle("-fx-background-color: #88ff85");


            plus.setOnAction(event -> plusQuantity(laundryQuantity, clothingObject ));



            Label laundryPrice = new Label("" + clothingArrayList.get(i).getPrice() + "DKK");
            laundryPrice.setLayoutX(360);
            laundryPrice.setLayoutY(25);



            laundryInfo.getChildren().addAll(logo,laundryType,minus,laundryQuantity,plus,laundryPrice);
            content.getChildren().addAll(laundryInfo);
        }



        root.getChildren().addAll(content,sp);
    }

    private void minusQuantity(TextField source, Clothing clothing) {
        clothing.setCount(-1);
        int quantity = clothing.getCount();
        double priceTest = 0;

        for (int i = 0; i < clothingArrayList.size(); i++) {
            double test = clothingArrayList.get(i).getCount();
            double test1 = clothingArrayList.get(i).getPrice();
            priceTest += test1 * test;

        }
        source.setText("" + (quantity));
        totalPrices.setText("" + priceTest );

    }

    private void plusQuantity(TextField source, Clothing clothing) {

        clothing.setCount(1);
        int quantity = clothing.getCount();
        double priceTest = 0;

        for (int i = 0; i < clothingArrayList.size(); i++) {
            double test = clothingArrayList.get(i).getCount();
            double test1 = clothingArrayList.get(i).getPrice();
            priceTest += test1 * test;

        }
        source.setText("" + (quantity));
        totalPrices.setText("" + priceTest);

        totalPrice.textProperty().bind(totalPrices.textProperty());


    }

    public void customerInfo(){
        customerPhone.setLayoutX(305);
        customerPhone.setLayoutY(100);
        customerPhone.setPrefWidth(480);
        customerPhone.setPrefHeight(50);
        customerPhone.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        customerPhone.setPromptText("Enter customer phone number");
        customerPhone.setFocusTraversable(false);
        customerPhone.setTooltip(new Tooltip("Enter customer phone number"));
        customerPhone.setAlignment(Pos.CENTER);


        customerPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    customerPhone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        newCustomer = new RadioButton("New Customer");
        newCustomer.setToggleGroup(group);
        newCustomer.setSelected(true);
        newCustomer.setLayoutX(810);
        newCustomer.setLayoutY(100);
        existingCustomer = new RadioButton("Existing Customer");
        existingCustomer.setToggleGroup(group);
        existingCustomer.setLayoutX(810);
        existingCustomer.setLayoutY(125);
        existingCustomer.setOnAction(event -> autoComplete());

        customerName = new TextField();
        customerName.setLayoutX(305);
        customerName.setLayoutY(640);
        customerName.setPrefWidth(480);
        customerName.setPrefHeight(50);
        customerName.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        customerName.setPromptText("Enter customer phone number");
        customerName.setFocusTraversable(false);
        customerName.setTooltip(new Tooltip("Enter customer phone number"));
        customerName.setAlignment(Pos.CENTER);


        root.getChildren().addAll(customerPhone, newCustomer, existingCustomer, customerName);
    }

    public void autoComplete() {
        ControllerPartner controller = new ControllerPartner();
        controller.getExisitingCustomerPhoneNo();
        customerPhoneNo = controller.customerPhoneNo;
        customerPhone.getEntries().addAll(customerPhoneNo);

        if(customerPhone != null) {
            customerName.setText("test");
        }

    }

    public void createOrderBtn(){
        createOrderBtn = new Button("Create order");
        createOrderBtn.setLayoutX(305);
        createOrderBtn.setLayoutY(710);
        createOrderBtn.setPrefWidth(480);
        createOrderBtn.setPrefHeight(50);
        createOrderBtn.setStyle("-fx-background-color: #34ffb9");
        createOrderBtn.setFont(new Font(18));
        createOrderBtn.setOnAction(event -> createOrderInDB());

        root.getChildren().addAll(createOrderBtn);
    }

    public void createOrderInDB() {
        if (newCustomer.isSelected()) {
            System.out.println("Virker det?");
            ControllerPartner controller = new ControllerPartner();
            controller.createOrderNewCustomer(customerPhone.getText(), customerName.getText(), totalPrice.getText());
        } else if (existingCustomer.isSelected()) {
           autoComplete();
        }
    }



    public void quantity(TextField quantity,Button quantityMinus,Button quantityPlus, int quantityX, int quantityY, int minusX, int minusY, int plusX, int plusY){
        quantity = new TextField("0");
        quantity.setLayoutX(quantityX);
        quantity.setLayoutY(quantityY);
        quantity.setPrefWidth(70);
        quantity.setPrefHeight(30);
        quantity.setFocusTraversable(false);
        quantity.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        quantity.setAlignment(Pos.CENTER);

        quantityMinus = new Button("-");
        quantityMinus.setLayoutX(minusX);
        quantityMinus.setLayoutY(minusY);
        quantityMinus.setPrefWidth(32);
        quantityMinus.setStyle("-fx-background-color: #88ff85");

        quantityPlus = new Button("+");
        quantityPlus.setLayoutX(plusX);
        quantityPlus.setLayoutY(plusY);
        quantityPlus.setPrefWidth(32);
        quantityPlus.setStyle("-fx-background-color: #88ff85");


        root.getChildren().addAll(quantity,quantityMinus,quantityPlus);
    }

    public void showRegister() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

