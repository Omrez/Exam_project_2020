package Presentation;

import Service.DB;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application {

    Group root;
    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    public static Stage loginStage;
    public static Scene scene;

    TextField username;
    PasswordField password;
    Button login;
    Label message;




    @Override
    public void start(Stage primaryStage) throws Exception {

        sceneRoot();
        login();


    }

    public void sceneRoot(){
        loginStage = new Stage();

        root = new Group();
        scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));

        loginStage.setTitle("Dry Cleaning System");
        loginStage.setScene(scene);
        loginStage.show();
    }

    public void login(){


        username = new TextField();
        username.setPrefWidth(310);
        username.setPrefHeight(50);
        username.setLayoutX(460);
        username.setLayoutY(300);
        username.setPromptText("Enter Username");
        username.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        username.setFocusTraversable(false);
        username.setTooltip(new Tooltip("Enter your username"));


        password = new PasswordField();
        password.setPrefWidth(310);
        password.setPrefHeight(50);
        password.setLayoutX(460);
        password.setLayoutY(370);
        password.setPromptText("Enter Password");
        password.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        password.setFocusTraversable(false);
        password.setTooltip(new Tooltip("Enter your Password"));

        login = new Button("Login");
        login.setPrefWidth(310);
        login.setPrefHeight(50);
        login.setLayoutX(460);
        login.setLayoutY(470);
        login.setStyle("-fx-background-color: #34ffb9");
        login.setFont(new Font(18));
        //login.hoverProperty();
        login.setOnMouseClicked(event -> handleLogin());

        message = new Label();
        message.setTextFill(Color.RED);
        message.setLayoutX(460);
        message.setLayoutY(430);
        message.setVisible(false);




        root.getChildren().addAll(username,message, password, login);
    }


    public void handleLogin(){
        DB db = new DB();
        String sql = "select * from tblUserAccount where fldUsername = '"+ username.getText()+"'";
        db.checkLogin(sql);
        db.checkAccountType(sql);

        if (username.getText().equals(db.username) && password.getText().equals(db.userPassword)){
            System.out.println("LOL it worked");

            if(db.accountType.equals("1")) {
                loginStage.close();
                Register register = new Register();
                register.showRegister();
            } else if (db.accountType.equals("2")) {

            } else if (db.accountType.equals("3")) {
                loginStage.close();
                DriverOrder driverOrder = new DriverOrder();
                driverOrder.showDriver();

            } else if (db.accountType.equals("4")) {
                loginStage.close();
                Admin admin = new Admin();
                admin.showAdmin();
            }

        } else {
            password.clear();
            password.setStyle("-fx-focus-color: RED");
            password.requestFocus();
            message.setVisible(true);
            message.setText("Wrong Username & Password, try again.");

            System.out.println("I didnt work");
        }
        //System.out.println("Clicked");

    }


}
