package Presentation;

import Application.*;
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

/**
 * The Register class is the UI where the Shop Assistant at the pickup Points create a customer order.
 */

public class Register extends Application {
    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    private Stage primaryStage;
    private ArrayList<Clothing> clothingArrayList;
    private ArrayList<String> customerPhoneNo;
    private TextField totalPrices = new TextField();
    private TextField customerName;
    private TextField customerPhone = new TextField();
    private Button createOrderBtn;
    private AnchorPane content;
    private AnchorPane laundryInfo;
    private Label totalPricePlaceholder;
    private Label totalPrice;
    final ToggleGroup radiobuttonGroup = new ToggleGroup();
    private RadioButton newCustomer;
    private RadioButton existingCustomer;
    private Group root;


    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
        customerInfo();
        createOrderBtn();
        content();
    }

        /**
         * showRegister is used to load/open the Register UI from another class.
         */
    public void showRegister() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sceneRoot is the root where the stage and scene is set and shown.
     */
    public void sceneRoot(){
        primaryStage = new Stage();
        root = new Group();
        Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));
        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        totalPricePlaceholder = new Label("Total Price: ");
        totalPricePlaceholder.setLayoutX(950);
        totalPricePlaceholder.setLayoutY(300);
        totalPricePlaceholder.setFont(new Font(22));

        totalPrice = new Label("0DKK");
        totalPrice.setLayoutX(985);
        totalPrice.setLayoutY(350);
        totalPrice.setFont(new Font(22));
        totalPrice.setTextFill(Color.CORAL);

        root.getChildren().addAll(totalPricePlaceholder,totalPrice);
    }

    /**
     * content is used to create the UI elements.
     */
    public void content(){
        content = new AnchorPane();
        content.setLayoutX(305);
        content.setLayoutY(180);
        content.setPrefWidth(480);
        content.setPrefHeight(430);
        ControllerPartner controller = new ControllerPartner();
        controller.getClothing();
        clothingArrayList = controller.clothingArrayList;
        content.setStyle("-fx-background-color: #b8cfcc");

        ScrollPane sp = new ScrollPane();
        sp.setContent(content);
        sp.setLayoutX(305);
        sp.setLayoutY(180);
        sp.setPrefSize(480,430);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setStyle("-fx-background-color: transparent");

        for (int i = 0; i < clothingArrayList.size(); i++) {
            laundryInfo = new AnchorPane();
            laundryInfo.setPrefWidth(480);
            laundryInfo.setPrefHeight(80);
            laundryInfo.setLayoutY(i*100);
            laundryInfo.setStyle("-fx-background-color: #91b1ad");

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

    /**
     * minusQuanity is used every time the - button is clicked in the UI.
     * It subtract the quantity of a clothing item.
     * @param source is the TextField that is changed.
     * @param clothing is the clothing object.
     */

    private void minusQuantity(TextField source, Clothing clothing) {
        clothing.setCount(-1);
        int quantity = clothing.getCount();
        double totalPriceOfClothing = 0;

        for (int i = 0; i < clothingArrayList.size(); i++) {
            double countOfItems = clothingArrayList.get(i).getCount();
            double clothingPricePerUnit = Double.valueOf(clothingArrayList.get(i).getPrice());
            totalPriceOfClothing += clothingPricePerUnit * countOfItems;

        }
        source.setText("" + (quantity));
        totalPrices.setText("" + totalPriceOfClothing );

    }

    /**
     * PlusQuantity is used every time the + button is clicked in the UI.
     * It add to the quantity of a clothing item.
     * @param source
     * @param clothing
     */
    private void plusQuantity(TextField source, Clothing clothing) {
        clothing.setCount(1);
        int quantity = clothing.getCount();
        double totalPriceOfClothing = 0;

        for (int i = 0; i < clothingArrayList.size(); i++) {
            double countOfItems = clothingArrayList.get(i).getCount();
            double clothingPricePerUnit = Double.valueOf(clothingArrayList.get(i).getPrice());
            totalPriceOfClothing += countOfItems * clothingPricePerUnit;

        }
        source.setText("" + (quantity));
        totalPrices.setText("" + totalPriceOfClothing);

        totalPrice.textProperty().bind(totalPrices.textProperty());
    }

    /**
     * customerInfo creates the UI elements for creating a new order and inserting customer info
     */

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
        newCustomer.setToggleGroup(radiobuttonGroup);
        newCustomer.setSelected(true);
        newCustomer.setLayoutX(810);
        newCustomer.setLayoutY(100);
        existingCustomer = new RadioButton("Existing Customer");
        existingCustomer.setToggleGroup(radiobuttonGroup);
        existingCustomer.setLayoutX(810);
        existingCustomer.setLayoutY(125);

        customerName = new TextField();
        customerName.setLayoutX(305);
        customerName.setLayoutY(640);
        customerName.setPrefWidth(480);
        customerName.setPrefHeight(50);
        customerName.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        customerName.setPromptText("Enter customer name");
        customerName.setFocusTraversable(false);
        customerName.setTooltip(new Tooltip("Enter customer name"));
        customerName.setAlignment(Pos.CENTER);


        root.getChildren().addAll(customerPhone, newCustomer, existingCustomer, customerName);
    }


    /**
     * createOrderBtn creates a button and sets a button action.
     */
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

    /**
     * createOrderInDB checks which radiobuttion is selected. If it is a new or existing customer,
     * and get the data provided from the TextFields and inserts it into the database.
     */
    public void createOrderInDB() {
        if (newCustomer.isSelected()) {
            System.out.println("Virker det?");
            ControllerPartner controller = new ControllerPartner();
            controller.createOrderNewCustomer(customerPhone.getText(), customerName.getText(), totalPrice.getText());
            Label successMessage = new Label("Order is created, an sms with invoice is sent to the customer");
            successMessage.setLayoutX(305);
            successMessage.setLayoutY(800);
            successMessage.setTextFill(Color.CORAL);
            root.getChildren().add(successMessage);


            customerName.clear();
            customerPhone.clear();

        } else if (existingCustomer.isSelected()) {
        }
    }

}

