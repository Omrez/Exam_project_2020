package Presentation;

import Domain.UserAccount;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class LogIn extends Application {

    Group root;
    private int sceneWidth = 1200;
    private int sceneHeight = 900;

    TextField email;
    PasswordField password;
    Button logIn;




    @Override
    public void start(Stage primaryStage) throws Exception {

        sceneRoot();
        logIn();


    }

    public void sceneRoot(){
        Stage primaryStage = new Stage();

        root = new Group();
        Scene scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));

        primaryStage.setTitle("Dry Cleaning System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logIn(){


        email = new TextField();
        email.setPrefWidth(310);
        email.setPrefHeight(50);
        email.setLayoutX(460);
        email.setLayoutY(300);
        email.setPromptText("Enter Email");
        email.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        email.setFocusTraversable(false);
        email.setTooltip(new Tooltip("Enter your Email"));


        password = new PasswordField();
        password.setPrefWidth(310);
        password.setPrefHeight(50);
        password.setLayoutX(460);
        password.setLayoutY(370);
        password.setPromptText("Enter Password");
        password.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        password.setFocusTraversable(false);
        password.setTooltip(new Tooltip("Enter your Password"));

        logIn = new Button("Login");
        logIn.setPrefWidth(310);
        logIn.setPrefHeight(50);
        logIn.setLayoutX(460);
        logIn.setLayoutY(470);
        logIn.setStyle("-fx-background-color: #34ffb9");
        logIn.setFont(new Font(18));
        logIn.hoverProperty();


        root.getChildren().addAll(email,password,logIn);

        handleLogIn();
    }

    public void handleLogIn(){

        logIn.setOnMouseClicked((e) -> {
            UserAccount user = new UserAccount();
            user.selectUser("select fldEmail from tblPartnerEmployee where fldEmail = '"+email.getText()+"'");

            if (email.getText().equals(user.getUserEmail())){
                System.out.println("LOL it worked");
            }

            //System.out.println("Clicked");

        });

    }


}
