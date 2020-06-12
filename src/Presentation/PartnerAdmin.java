package Presentation;

import Application.ControllerPartner;
import Domain.Driver;
import Domain.Partner;
import Domain.PartnerEmployee;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PartnerAdmin extends Application {
    private AnchorPane root;
    private Scene scene;
    private Stage primaryStage;

    private AnchorPane menu;
    private AnchorPane showEmployee;
    private AnchorPane createEmployee;
    private TableView tableViewEmployee = new TableView();

    private int sceneWidth = 1200;
    private int sceneHeight = 900;
    private ArrayList<PartnerEmployee> partnerEmployeeArrayList;
    ControllerPartner controller = new ControllerPartner();



    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneRoot();
        menu();
        showEmployee();
        createEmployee();

    }

    public void showPartnerAdmin() {
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void menu(){
        menu = new AnchorPane();
        menu.setPrefWidth(sceneWidth+20);
        menu.setPrefHeight(80);

        root.getChildren().addAll(menu);
    }

    public void showEmployee(){
        showEmployee = new AnchorPane();
        showEmployee.setPrefWidth(600);
        showEmployee.setLayoutY(80);
        showEmployee.setPrefHeight(sceneHeight);
        root.getChildren().addAll(showEmployee);

        controller.getPartnerEmployee();
        partnerEmployeeArrayList = controller.partnerEmployees;
        createTableViewEmployee();

    }

    public void createTableViewEmployee(){
        tableViewEmployee.setEditable(true);
        tableViewEmployee.setPrefHeight(800);
        tableViewEmployee.setPrefWidth(600);

        TableColumn<PartnerEmployee, String> columnPartnerEmployeeUsername = new TableColumn<>("Employee Username");
        columnPartnerEmployeeUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPartnerEmployeeUsername.setCellFactory(TextFieldTableCell.forTableColumn());
        //columnPartnerName.setOnEditCommit(event -> updateCellInDB(TableColumn.CellEditEvent.ANY);
        columnPartnerEmployeeUsername.setOnEditCommit(event -> {
            PartnerEmployee partnerEmployee = event.getRowValue();
            partnerEmployee.setUsername(event.getNewValue());
            controller.updateDBPartnerEmployeeUsername(partnerEmployee.getPartnerEmployeeID(), partnerEmployee.getUsername());
        });



        TableColumn<PartnerEmployee, String> columnPartnerEmployeePassword = new TableColumn<>("Password");
        columnPartnerEmployeePassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnPartnerEmployeePassword.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPartnerEmployeePassword.setOnEditCommit(event -> {
            PartnerEmployee partnerEmployee = event.getRowValue();
            partnerEmployee.setPassword(event.getNewValue());
            controller.updateDBPartnerEmployeePassword(partnerEmployee.getPartnerEmployeeID(), partnerEmployee.getPassword());
        });



        if (!tableViewEmployee.getColumns().isEmpty()) {

        } else  {
            tableViewEmployee.getColumns().add(columnPartnerEmployeeUsername);
            tableViewEmployee.getColumns().add(columnPartnerEmployeePassword);


            for (int i = 0; i < partnerEmployeeArrayList.size() ; i++) {
                tableViewEmployee.getItems().add(partnerEmployeeArrayList.get(i));

            }

        }
        showEmployee.getChildren().add(tableViewEmployee);

    }

    public void createEmployee(){
        createEmployee = new AnchorPane();
        createEmployee.setPrefWidth(620);
        createEmployee.setPrefHeight(sceneHeight);
        createEmployee.setLayoutY(80);
        createEmployee.setLayoutX(600);

        Label createEmploy = new Label("Create Employee");
        createEmploy.setLayoutX(230);
        createEmploy.setLayoutY(30);
        createEmploy.setFont(new Font(18));

        TextField username = new TextField();
        username.setPrefWidth(400);
        username.setPrefHeight(70);
        username.setLayoutX(100);
        username.setLayoutY(100);
        username.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        username.setFocusTraversable(false);
        username.setTooltip(new Tooltip("Username"));
        username.setAlignment(Pos.CENTER);
        username.setPromptText("Username");


        PasswordField password = new PasswordField();
        password.setPrefWidth(400);
        password.setPrefHeight(70);
        password.setLayoutX(100);
        password.setLayoutY(200);
        password.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #eaebff; -fx-prompt-text-fill: gray");
        password.setFocusTraversable(false);
        password.setTooltip(new Tooltip("password"));
        password.setAlignment(Pos.CENTER);
        password.setPromptText("password");

        Button createEmployeeBtn = new Button("Create Employee");
        createEmployeeBtn.setPrefWidth(400);
        createEmployeeBtn.setPrefHeight(70);
        createEmployeeBtn.setLayoutX(100);
        createEmployeeBtn.setLayoutY(300);
        createEmployeeBtn.setStyle("-fx-background-color: #34ffb9");
        createEmployeeBtn.setFont(new Font(18));

        createEmployee.getChildren().addAll(username,password,createEmploy,createEmployeeBtn);
        root.getChildren().addAll(createEmployee);
    }
}
