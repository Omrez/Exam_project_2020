package Application;

import Domain.Clothing;
import Service.DB;

import java.util.ArrayList;
import java.util.Random;

public class ControllerPartner {
    public ArrayList<Clothing> clothingArrayList;
    public ArrayList<String> customerPhoneNo;
    DB db = new DB();


    public void getClothing() {
        clothingArrayList = db.getClothing();
    }


    public void createOrderNewCustomer(String phoneNO, String name, String totalPrice) {
        Random rnd = new Random();
        int customerID = rnd.nextInt(1000000);
        int orderNumber = rnd.nextInt(1000000);
        String sql1 = "INSERT INTO [tblCustomer] VALUES ('"+ name +"', 'deleteThisField', '"+phoneNO + "', '"+ customerID +"')";
        String sql2 = "Select * from tblCustomer where fldPhone_no ='"+ phoneNO +"'";
        //String sql3 = "INSERT INTO [tblCustomerOrder] VALUES ('" +orderNumber +"', GETDATE(), '"+ totalPrice + "','"+customerID +"')";
        String sql4 = "INSERT INTO [tblOrder] VALUES ('"+orderNumber + "',GETDATE(),'"+ totalPrice + "','1')";
        db.createOrderNewCustomer(sql1, sql2, sql4, String.valueOf(orderNumber), totalPrice);

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



}
