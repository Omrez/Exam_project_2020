package Application;

import Domain.Clothing;
import Domain.Driver;
import Domain.Order;
import Domain.Partner;
import Service.DB;

import java.util.ArrayList;

/**
 * This is the controller for the Admin class
 */

public class ControllerAdmin {

    public ArrayList<Partner> partnerArrayList;
    public ArrayList<Driver> driverArrayList;
    public ArrayList<Clothing> clothingArrayList;
    public ArrayList<Order> orderInfo;
    DB db = new DB();


    public void getClothing() {

        clothingArrayList = db.getClothing();
    }


    public void getPartners(){
        partnerArrayList = db.getPartners();
    }

    public void getDrivers(){
        driverArrayList = db.getDrivers();
    }

    public void createPartner(String name, String email, String address, String phoneNo, String code, String password, String zipCity ) {
        String sql = "INSERT INTO [tblPartner] VALUES ('"+ name + "', '"+ email +"', '" + phoneNo +"','" + address + "','"+ code + "','" + password + "','" + zipCity + "')";
        db.createPartner(sql);

        partnerArrayList = db.getPartners();
    }

    public void createLaundry(String type, String price ) {
        String sql = "INSERT INTO [tblLaundryPrice] VALUES ('"+ type + "', '"+ price +"')";
        db.genericConnection(sql);

        clothingArrayList = db.getClothing();
    }

    public void createUserAccountPartner(String username, String password ) {
        String sql = "INSERT INTO [tblUserAccount] VALUES ('"+ username + "', '"+ password +"', '2', '0')";
        db.createPartner(sql);

    }

    public void getOrder(){
        orderInfo = db.getOrder();
    }

    public void updateDBPartnerName(String partnerID, String name) {
        String sql = "UPDATE tblPartner SET fldName = '"+ name + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBPartnerPhoneNo(String partnerID, String phoneNo) {
        String sql = "UPDATE tblPartner SET fldPhone_no = '"+ phoneNo + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBPartnerEmail(String partnerID, String email) {
        String sql = "UPDATE tblPartner SET fldEmail = '"+ email + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBPartnerAddress(String partnerID, String address) {
        String sql = "UPDATE tblPartner SET fldAddress = '"+ address + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBDriverName(String partnerID, String name) {
        String sql = "UPDATE tblDrivers SET fldName = '"+ name + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBDriverPhoneNo(String partnerID, String phoneNo) {
        String sql = "UPDATE tblDrivers SET fldPhone_no = '"+ phoneNo + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBDriverEmail(String partnerID, String email) {
        String sql = "UPDATE tblDrivers SET fldEmail = '"+ email + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBClothingType(String clothingID, String type) {
        String sql = "UPDATE tblLaundryPrice SET fldType = '"+ type + "'  WHERE fldLaundryPrice_id = '"+ clothingID +"'";
        db.updateDBInfo(sql);
    }

    public void updateDBClothingPrice(String clothingID, String price) {
        String sql = "UPDATE tblLaundryPrice SET fldPrice = '"+ price+ "'  WHERE fldLaundryPrice_id = '"+ clothingID +"'";
        db.updateDBInfo(sql);
    }

    public void createDriver(String name, String email, String phone, String password) {
        String sql = "INSERT INTO tblDrivers VALUES ('"+ name + "', '"+ phone +"', '" + email+ "')";
        String sql1 = "INSERT INTO tblUserAccount VALUES ('"+ email + "', '"+ password +"', '3', '0')";
        db.genericConnection(sql);
        db.genericConnection(sql1);

        driverArrayList = db.getDrivers();
    }
}
