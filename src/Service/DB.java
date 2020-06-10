package Service;

import Domain.Clothing;
import Domain.Driver;
import Domain.Order;
import Domain.Partner;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class DB implements Initializable {

    String url = "jdbc:sqlserver://LAPTOP-EQK4SQO2\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
    String user = "sa";
    String password = "123456";

    String type;
    String price;

    String orderNumber;
    String orderDate;
    String orderPrice;

    public String username;
    public String userPassword;
    public String accountType;
    public ArrayList<Order> OrderArrayList;
    public ObservableList<Partner> partnerArrayList = FXCollections.observableArrayList();;
    public ArrayList<Driver> driverArrayList;
    public ArrayList<Clothing> clothingArrayList;
    public ArrayList<String> phoneNoArrayList;
    int index = 0;

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partnerArrayList.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("Data invalidated");

            }
        });

    }



    public void checkLogin(String sql) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                username = result.getString("fldUsername");
                userPassword = result.getString("fldPassword");
                System.out.println(username);
                System.out.println(userPassword);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void checkAccountType(String sql) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                accountType = result.getString("fldLoginType");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Clothing> getClothing() {
        clothingArrayList = new ArrayList<>();
        String sql = "SELECT * FROM tblLaundryPrice";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                price = result.getString("fldPrice");
                Clothing clothing = new Clothing();
                clothing.setPrice(Double.valueOf(result.getString("fldPrice")));
                clothing.setType(result.getString("fldType"));
                clothingArrayList.add(clothing);




            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clothingArrayList;
    }

    public ArrayList<Order> getOrder(){
        OrderArrayList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblOrder\n" +
                    "JOIN tblPartner ON tblOrder.fldPartner_id = tblPartner.fldPartner_id\n" +
                    "JOIN tblCity ON tblPartner.fldCity_id = tblCity.fldCity_id";
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                orderNumber = result.getString("fldOrderNumber");
                orderDate = result.getString("fldDate");
                orderPrice = result.getString("fldPrice");
                Order order = new Order();
                order.setOrderNumber(orderNumber);
                order.setOrderDate(orderDate);
                order.setOrderPrice(orderPrice);
                order.setPartnerName(result.getString("fldName"));
                order.setAddress(result.getString("fldAddress"));
                order.setCity(result.getString("fldCityName"));
                OrderArrayList.add(order);
                //System.out.println(test);

                //System.out.println(test);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(OrderArrayList.get(3));
        return OrderArrayList;
    }

    public ObservableList<Partner> getPartners() {

        String sql = "select * from tblPartner";
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Partner partner = new Partner();
                partner.setName(result.getString("fldName"));
                partner.setAddress(result.getString("fldAddress"));
                partner.setPhoneNo(result.getString("fldPhone_no"));
                partner.setEmail(result.getString("fldEmail"));
                partnerArrayList.add(partner);
                //System.out.println(partnerArrayList);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return partnerArrayList;

    }

    public ArrayList<Driver> getDrivers() {
        driverArrayList = new ArrayList<>();

        String sql = "select * from tblDrivers";
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Driver driver = new Driver();
                driver.setName(result.getString("fldName"));
                driver.setPhoneNo(result.getString("fldPhone_no"));
                driverArrayList.add(driver);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return driverArrayList;

    }

    public void createPartner(String sql) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createOrderNewCustomer(String sqlTblCustomer, String sqlFindCustomerID,  String sqlTblOrder, String orderNumber, String totalPrice) {
        try {
            String customerID = "";

            Connection con = DriverManager.getConnection(url, user, password);

            con.prepareStatement(sqlTblCustomer).execute();


            PreparedStatement statement = con.prepareStatement(sqlFindCustomerID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                customerID = result.getString("fldCustomer_id");

            }

            String sql3 = "INSERT INTO [tblCustomerOrder] VALUES ('" +orderNumber +"', GETDATE(), '"+ totalPrice + "','"+customerID +"')";

            PreparedStatement statement2 = con.prepareStatement(sql3);
            statement2.execute();
            PreparedStatement statement3 = con.prepareStatement(sqlTblOrder);
            statement3.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createOrderExistingCustomer(String sqlTblCustomer, String sqlFindCustomerID,  String sqlTblOrder, String orderNumber, String totalPrice) {
        try {
            String customerID = "";

            Connection con = DriverManager.getConnection(url, user, password);

            con.prepareStatement(sqlTblCustomer).execute();


            PreparedStatement statement = con.prepareStatement(sqlFindCustomerID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                customerID = result.getString("fldCustomer_id");

            }

            String sql3 = "INSERT INTO [tblCustomerOrder] VALUES ('" +orderNumber +"', GETDATE(), '"+ totalPrice + "','"+customerID +"')";

            PreparedStatement statement2 = con.prepareStatement(sql3);
            statement2.execute();
            PreparedStatement statement3 = con.prepareStatement(sqlTblOrder);
            statement3.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> getAllPhoneNo(){
        phoneNoArrayList = new ArrayList<>();
        String phone;
        String sql = "SELECT * FROM tblCustomer";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                phone = result.getString("fldPhone_no");
                phoneNoArrayList.add(phone);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return phoneNoArrayList;
    }




}
