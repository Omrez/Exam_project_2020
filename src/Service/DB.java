package Service;

import Domain.*;
import Domain.Driver;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The DB class is where the connection to the Database is made. It executes all database queries in the whole system.
 */

public class DB {

    String url = "jdbc:sqlserver://LAPTOP-EQK4SQO2\\SQLEXPRESS;databaseName=Dry_Cleaning_Service2";
    String user = "sa";
    String password = "123456";

    String orderNumber;
    String orderDate;
    String orderPrice;

    public String username;
    public String userPassword;
    public String accountType;
    public ArrayList<Order> OrderArrayList;
    public ArrayList<Partner> partnerArrayList = new ArrayList<>();
    public ArrayList<Driver> driverArrayList;
    public ArrayList<Clothing> clothingArrayList;
    public ArrayList<String> phoneNoArrayList;
    public ArrayList<PartnerEmployee> partnerEmployeeArrayList;

    /**
     * checkLogin is used when a user wants to login to the system. It checks if the username and passwords are correct.
     * @param sql
     */
    public void checkLogin(String sql) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                username = result.getString("fldUsername");
                userPassword = result.getString("fldPassword");

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
        String sql = "SELECT * FROM tblLaundry";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Clothing clothing = new Clothing();
                clothing.setPrice(result.getString("fldPrice"));
                clothing.setType(result.getString("fldType"));
                clothing.setClothingID(result.getString("fldLaundry_id"));
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

    public ArrayList<Partner> getPartners() {
        partnerArrayList = new ArrayList<>();

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
                partner.setPartnerID(result.getString("fldPartner_id"));
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
                driver.setEmail(result.getString("fldEmail"));
                driver.setDriverID(result.getString("fldDriver_id"));
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

    public void genericConnection(String sql) {
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

    public ArrayList<PartnerEmployee> getPartnerEmployee() {
        partnerEmployeeArrayList = new ArrayList<>();

        String sql = "select * from tblUserAccount where fldLoginType = '1'";
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                PartnerEmployee partnerEmployee = new PartnerEmployee();
                partnerEmployee.setUsername(result.getString("fldUsername"));
                partnerEmployee.setPassword(result.getString("fldPassword"));
                partnerEmployee.setPartnerEmployeeID(result.getString("fldUserAccount_id"));
                partnerEmployeeArrayList.add(partnerEmployee);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return partnerEmployeeArrayList;
    }



    public void updateDBInfo(String sql){

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
