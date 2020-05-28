package Presentation;

import Domain.Clothing;
import Service.DB;
import Application.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Register extends Application {
    Group root;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    private int count;

    TextField customerName;
    TextField customerPhone;
    Button createOrder;

    ImageView jeans;
    Label jeansLabel;
    TextField jeansQuan;
    Button jeansMinus;
    Button jeansPlus;
    Label jeansPrice;

    ImageView jacket;
    Label jacketLabel;
    TextField jacketQuan;
    Button jacketMinus;
    Button jacketPlus;
    Label jacketPrice;

    ImageView shirt;
    Label shirtLabel;
    TextField shirtQuan;
    Button shirtMinus;
    Button shirtPlus;
    Label shirtPrice;

    ImageView suit;
    Label suitLabel;
    TextField suitQuan;
    Button suitMinus;
    Button suitPlus;
    Label suitPrice;

    ImageView carpet;
    Label carpetLabel;
    TextField carpetQuan;
    Button carpetMinus;
    Button carpetPlus;
    Label carpetPrice;

    @Override
    public void start(Stage primaryStage) throws Exception {
            sceneRoot();
            customerFields(customerName, 305,100,"Enter customer name","Enter customer name");

            customerPhone();
            createOrderBtn();


            Controller controller = new Controller();
            controller.initialize();





            /*createLaundry(this.jeans,"Presentation/images/jeans.png",50,330,200,jeansLabel,, 400,220);
            quantity(jeansQuan, jeansMinus,jeansPlus,550,215, 512,215,625,215);
            laundryPrices(jeansPrice,laundry.getPrice()+" DKK", 680,220);*/

            /*
            createLaundry(jacket,"Presentation/images/jacket.png",40,335,280,jacketLabel,laundry.getJacketType(),400,300);
            quantity(jacketQuan,jacketMinus,jacketPlus,550,290,512,290,625,290);
            laundryPrices(jacketPrice,laundry.getJacketPrice()+" DKK",680, 295);

            createLaundry(shirt, "Presentation/images/shirt.png",50,330,360,shirtLabel,laundry.getShirtType(),400,380);
            quantity(shirtQuan,shirtMinus,shirtPlus,550,370,512,370,625,370);
            laundryPrices(shirtPrice,laundry.getShirtPrice()+" DKK",680,375);

            createLaundry(suit, "Presentation/images/suit.png",50,330,440,suitLabel,laundry.getSuitType(),400,460);
            quantity(suitQuan,suitMinus,suitPlus,550,450,512,450,625,450);
            laundryPrices(suitPrice,laundry.getSuitPrice()+" DKK", 680,455);

            createLaundry(carpet,"Presentation/images/carpet.png",45,330,520,carpetLabel,laundry.getCarpetType(),400,540);
            quantity(carpetQuan,carpetMinus,carpetPlus,550,530,512,530,625,530);
            laundryPrices(carpetPrice,laundry.getCarpetPrice()+" DKK",680,535);*/



    }

    public void sceneRoot(){
            Stage primaryStage = new Stage();
            root = new Group();
            Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));
            primaryStage.setTitle("Dry Cleaning System");
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public void customerFields(TextField customer, int customerX, int customerY, String customerText, String toolTip){
            customer = new TextField();
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

    public void customerPhone(){
        customerPhone = new TextField();
        customerPhone.setLayoutX(305);
        customerPhone.setLayoutY(640);
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

        root.getChildren().addAll(customerPhone);
    }

    public void createOrderBtn(){
            createOrder = new Button("Create order");
            createOrder.setLayoutX(305);
            createOrder.setLayoutY(710);
            createOrder.setPrefWidth(480);
            createOrder.setPrefHeight(50);
            createOrder.setStyle("-fx-background-color: #34ffb9");
            createOrder.setFont(new Font(18));

            root.getChildren().addAll(createOrder);
    }

    public void createLaundry(ImageView laundryName, String laundryURL,int laundryWidth, int laundryX, int laundryY, Label laundryLabel, String labelText, int labelX, int labelY){
            laundryName = new ImageView(laundryURL);
            laundryName.setFitWidth(laundryWidth);
            laundryName.setLayoutX(laundryX);
            laundryName.setLayoutY(laundryY);

            laundryLabel = new Label(labelText);
            laundryLabel.setLayoutX(labelX);
            laundryLabel.setLayoutY(labelY);

            root.getChildren().addAll(laundryName,laundryLabel);
    }

    public void laundryPrices(Label price,String priceText, int priceX, int priceY){
            price = new Label(priceText);
            price.setLayoutX(priceX);
            price.setLayoutY(priceY);

            root.getChildren().addAll(price);
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

            quantityMinus(quantityMinus,quantity);
            quantityPlus(quantityPlus,quantity);

            root.getChildren().addAll(quantity,quantityMinus,quantityPlus);
    }

    public void quantityPlus(Button plus, TextField quantity){

            plus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    count += 1;

                    quantity.setText("" + count);

                }
            });
    }

    public void quantityMinus(Button minus, TextField quantity){
            minus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    count -= 1;

                    quantity.setText("" + count);

                }
            });
    }







}
