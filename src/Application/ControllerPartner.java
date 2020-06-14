package Application;

import Domain.Clothing;
import Domain.PartnerEmployee;
import Service.DB;

import java.util.ArrayList;
import java.util.Random;

public class ControllerPartner {
    public ArrayList<Clothing> clothingArrayList;
    public ArrayList<String> customerPhoneNo;
    public ArrayList<PartnerEmployee> partnerEmployees;
    DB db = new DB();

    /**
     * getClothing method get info about clothing/laundry from DB class
     */

    public void getClothing() {
        clothingArrayList = db.getClothing();
    }

    /**
     * createOrderNewCustomer method creates a new order for customer, into the database
     *
     * @param phoneNO
     * @param name
     * @param totalPrice
     */

    public void createOrderNewCustomer(String phoneNO, String name, String totalPrice) {
        Random rnd = new Random();
        int customerID = rnd.nextInt(1000000);
        int orderNumber = rnd.nextInt(1000000);
        String sql1 = "INSERT INTO [tblCustomer] VALUES ('"+ name +"', 'deleteThisField', '"+phoneNO + "', '"+ customerID +"')";
        String sql2 = "Select * from tblCustomer where fldPhone_no ='"+ phoneNO +"'";
        //String sql3 = "INSERT INTO [tblCustomerOrder] VALUES ('" +orderNumber +"', GETDATE(), '"+ totalPrice + "','"+customerID +"')";
        String sql4 = "INSERT INTO [tblOrder] VALUES ('"+orderNumber + "',GETDATE(),'"+ totalPrice + "','1')";
        db.createOrderNewCustomer(sql1, sql2, sql4, String.valueOf(orderNumber), totalPrice);
        System.out.println("Dear " + name);
        System.out.println("Thanks for your order " + orderNumber);
        System.out.println("Total price is " + totalPrice);

    }

    public void createOrderExistingCustomer(String phoneNO, String name, String totalPrice) {
        Random rnd = new Random();
        int customerID = rnd.nextInt(1000000);
        int orderNumber = rnd.nextInt(1000000);
        String sql1 = "INSERT INTO [tblCustomer] VALUES ('"+ name +"', 'deleteThisField', '"+phoneNO + "', '"+ customerID +"')";
        String sql2 = "Select * from tblCustomer where fldPhone_no ='"+ phoneNO +"'";
        //String sql3 = "INSERT INTO [tblCustomerOrder] VALUES ('" +orderNumber +"', GETDATE(), '"+ totalPrice + "','"+customerID +"')";
        String sql4 = "INSERT INTO [tblOrder] VALUES ('"+orderNumber + "',GETDATE(),'"+ totalPrice + "','1')";
        db.createOrderNewCustomer(sql1, sql2, sql4, String.valueOf(orderNumber), totalPrice);

    }

    public void getExisitingCustomerPhoneNo() {
       customerPhoneNo = db.getAllPhoneNo();
    }

    /**
     * getPartnerEmployee method gets info about employee, from the DB class
     */

    public void getPartnerEmployee() {
        partnerEmployees = db.getPartnerEmployee();
    }

    /**
     * updateDBPartnerEmployeeUsername method update a new employee name, and inserts in the database
     *
     * @param partnerEmployeeID
     * @param username
     */

    public void updateDBPartnerEmployeeUsername(String partnerEmployeeID, String username) {
        String sql = "UPDATE tblUserAccount SET fldUsername = '"+ username + "'  WHERE fldUserAccount_id = '"+ partnerEmployeeID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBPartnerEmployeePassword method updates a new employee password, and inserts into the database
     *
     * @param partnerEmployeeID
     * @param password
     */

    public void updateDBPartnerEmployeePassword(String partnerEmployeeID, String password) {
        String sql = "UPDATE tblUserAccount SET fldPassword = '"+ password + "'  WHERE fldUserAccount_id = '"+ partnerEmployeeID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * createPartnerEmployee method creates a new employee, and insert into user account in the database
     *
     * @param username
     * @param password
     */

    public void createPartnerEmployee(String username, String password) {
        String sql = "INSERT INTO tblUserAccount VALUES ('"+username + "', '" + password+"', '1', '1')";
        db.genericConnection(sql);

        partnerEmployees = db.getPartnerEmployee();
    }



}
