package Presentation;

import Domain.Partner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class DriverOrder extends Application {

    Group root;

    private int sceneWidth = 1200;
    private int sceneHeight = 900;

    AnchorPane bp1;

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


        Label sp = new Label("dddfsfdsfsfdf");

        sp.setLayoutX(200);
        sp.setLayoutY(200);





    }

    public void orderDetails(AnchorPane anchorPane, int paneX, int paneY, Label partnerName, String nameText, Label partnerCity, String cityText, String addressText, Label orderNumber, String numberText, String dateText){

        List list = new ArrayList();
        list.add("peter");
        list.add("hans");
        list.add("lula");
        list.add("person");

        for (int i = 0; i <list.size() ; i++) {
            System.out.println("Array "+i);
        }


        /*for (int i = 0; i < 4; i++) {



            anchorPane = new AnchorPane();
            anchorPane.setStyle("-fx-background-color: red");
            anchorPane.setPrefWidth(1100);
            anchorPane.setPrefHeight(70);
            anchorPane.setLayoutX(50);
            anchorPane.getLayoutY();



            root.getChildren().addAll(anchorPane);
        }*/


        //anchorPane.setPadding(new Insets(20,20,25,20));

        /*partnerName = new Label("Partner name: "+nameText);
        partnerName.setLayoutX(20);
        partnerName.setLayoutY(25);
        partnerName.setTextFill(Color.CORAL);
        partnerName.setFont(new Font(15));

        partnerCity = new Label("City "+cityText);
        partnerCity.setLayoutX(200);
        partnerCity.setLayoutY(25);
        partnerCity.setLayoutX(200);
        partnerCity.setLayoutY(25);
        partnerCity.setTextFill(Color.CORAL);
        partnerCity.setFont(new Font(15));

        partnerAddress = new Label("Address "+addressText);
        partnerAddress.setLayoutX(400);
        partnerAddress.setLayoutY(25);
        partnerAddress.setTextFill(Color.CORAL);
        partnerAddress.setFont(new Font(15));

        anchorPane.getChildren().addAll(partnerName,partnerCity,partnerAddress);
*/

    }
}
