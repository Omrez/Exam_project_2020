package Presentation;
import Domain.Partner;
import Service.DB;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the login page(UI) for all Dry Cleaning User Accounts.
 */
public class Login extends Application {

    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    public static Stage loginStage;
    private Scene scene;
    private Group root;
    private TextField username;
    private PasswordField password;
    private Button login;
    private Label LoginErrorMessage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
        login(); //Creates the UI elements TextField, PasswordField and button and adds it to sceneRoot.
    }

    /**
     * sceneRoot is the root where the stage and scene is set and shown.
     */
    public void sceneRoot(){
        loginStage = new Stage();
        root = new Group();
        scene = new Scene(root,sceneWidth,sceneHeight, Color.web("#b8cfcc"));

        loginStage.setTitle("Dry Cleaning System");
        loginStage.setScene(scene);
        loginStage.show();
    }

    /**
     * login() creates the UI elements TextField, PasswordField and Button.
     */
    public void login(){

        username = new TextField();
        username.setPrefWidth(310);
        username.setPrefHeight(50);
        username.setLayoutX(50);
        username.setLayoutY(100);
        username.setPromptText("Enter Username");
        username.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        username.setFocusTraversable(false);
        username.setTooltip(new Tooltip("Enter your username"));

        password = new PasswordField();
        password.setPrefWidth(310);
        password.setPrefHeight(50);
        password.setLayoutX(50);
        password.setLayoutY(175);
        password.setPromptText("Enter Password");
        password.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        password.setFocusTraversable(false);
        password.setTooltip(new Tooltip("Enter your Password"));

        login = new Button("Login");
        login.setPrefWidth(310);
        login.setPrefHeight(50);
        login.setLayoutX(50);
        login.setLayoutY(250);
        login.setStyle("-fx-background-color: #34ffb9");
        login.setFont(new Font(18));
        login.setOnMouseClicked(event -> handleLogin());

        LoginErrorMessage = new Label();
        LoginErrorMessage.setTextFill(Color.RED);
        LoginErrorMessage.setLayoutX(50);
        LoginErrorMessage.setLayoutY(325);
        LoginErrorMessage.setVisible(false);

        Label loginLabel = new Label("LOGIN");
        loginLabel.setLayoutX(165);
        loginLabel.setLayoutY(30);
        loginLabel.setStyle("-fx-font-size: 20");
        loginLabel.setTextFill(Color.WHITE);



        AnchorPane loginInfoPane = new AnchorPane();
        loginInfoPane.setPrefHeight(400);
        loginInfoPane.setPrefWidth(400);
        //loginInfoPane.setStyle("-fx-background-color: darkgreen");
        loginInfoPane.setLayoutX(420);
        loginInfoPane.setLayoutY(250);
        //loginInfoPane.setStyle("-fx-border-radius: 400");
        loginInfoPane.setStyle("-fx-background-color: #91b1ad;" + "-fx-background-radius: 30 ");


        loginInfoPane.getChildren().addAll(loginLabel, username, LoginErrorMessage, password, login);
        root.getChildren().add(loginInfoPane);
    }

    /**
     * handleLogin() handles the login functionality, it make call to DB to check if username and password is correct
     * and what type of UserAccount is logging in.
     *
     * AccountType 1 is the PartnerEmployee(Shop Assistant)
     *
     * AccountType 2 is the partner Admin(Boss at the local shop) who needs to be able to create accounts for their
     * employees(Shop Assistants)
     *
     * AccountType 3 is the Driver, who needs to be able to see orders from partners(Pickup points).
     *
     * AccountType 4 is the Admin(Administrator) who has total access to all aspects of the system.
     */
    public void handleLogin(){
        DB db = new DB();
        String sql = "select * from tblUserAccount where fldUsername = '"+ username.getText()+"'";
        db.checkLogin(sql);
        db.checkAccountType(sql);

        //The if statement below checks if the username and password provided is correct.
        if (username.getText().equals(db.username) && password.getText().equals(db.userPassword)){
            //the switch statements below checks what AccountType is logging on and opens the dashboard for that type.
            switch (db.accountType) {
                case "1":// Partner employee(Shop Assistant)
                    loginStage.close();
                    Register register = new Register();
                    register.showRegister();
                    break;
                case "2": //Partner Admin(Boss at pickup point)
                    loginStage.close();
                    PartnerAdmin partnerAdmin = new PartnerAdmin();
                    partnerAdmin.showPartnerAdmin();

                    break;
                case "3": //Driver that pickup the laundry.
                    loginStage.close();
                    DriverOrder driverOrder = new DriverOrder();
                    driverOrder.showDriver();

                    break;
                case "4": //Admin(Administrator)
                    loginStage.close();
                    Admin admin = new Admin();
                    admin.showAdmin();
                    break;
            }

        } else {
            password.clear();
            password.setStyle("-fx-focus-color: RED");
            password.requestFocus();
            LoginErrorMessage.setVisible(true);
            LoginErrorMessage.setText("Wrong Username & Password, try again.");
        }

    }
}