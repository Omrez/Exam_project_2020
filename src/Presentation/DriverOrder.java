package Presentation;

import Domain.Partner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DriverOrder extends Application {

    Group root;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;

    BorderPane bp1;

    Label driverName;
    Label partnerName;
    Label partnerCity;
    Label partnerAddress;
    Label orderNumber;
    Label orderDate;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();

        Partner partner = new Partner();
        partner.selectPartner("select fldName,fldAddress from tblPartner where fldPartner_id = '1'");

        orderDetails(bp1,50,200,partnerName,partner.getPartnerName(),partnerCity,"søndeborg","vej 2", orderNumber, "0003232","22-02-2020");
    }

    public void sceneRoot(){
        Stage primaryStage = new Stage();

        root = new Group();
        Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));
        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public void orderDetails(BorderPane borderPane, int paneX, int paneY, Label partnerName, String nameText,Label partnerCity, String cityText, String addressText, Label orderNumber, String numberText,String dateText){
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: red");
        borderPane.setPrefWidth(1100);
        borderPane.setPrefHeight(70);
        borderPane.setLayoutX(paneX);
        borderPane.setLayoutY(paneY);
        borderPane.setPadding(new Insets(20,20,25,20));

        partnerName = new Label(nameText);
        partnerCity = new Label(cityText+addressText);
        orderNumber = new Label("Order: "+numberText+"  "+"Date: "+dateText);

        borderPane.setLeft(partnerName);
        borderPane.setCenter(partnerCity);
        borderPane.setRight(orderNumber);
        root.getChildren().addAll(borderPane);
    }
}
