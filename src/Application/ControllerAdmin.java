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

    /**
     * getClothing method gets clothing/laundry from the DB class
     */

    public void getClothing() {

        clothingArrayList = db.getClothing();
    }

    /**
     * getPartners method gets all info about partner from DB class
     */

    public void getPartners(){
        partnerArrayList = db.getPartners();
    }

    /**
     * getDrivers method gets all info about driver from DB class
     */

    public void getDrivers(){
        driverArrayList = db.getDrivers();
    }

    /**
     * createPartner method inserts a new partner into the Database
     *
     * @param name
     * @param email
     * @param address
     * @param phoneNo
     * @param zipCity
     */

    public void createPartner(String name, String email, String address, String phoneNo, String zipCity ) {
        String sql = "INSERT INTO tblPartner VALUES ('"+ name + "', '"+ email +"', '" + address +"','" + phoneNo + "','" + zipCity + "')";
        db.createPartner(sql);

        partnerArrayList = db.getPartners();
    }

    /**
     * createLaundry method inserts a new clothing/laundry into the Database
     *
     * @param type
     * @param price
     */

    public void createLaundry(String type, String price ) {
        String sql = "INSERT INTO tblLaundry VALUES ('"+ type + "', '"+ price +"')";
        db.genericConnection(sql);

        clothingArrayList = db.getClothing();
    }

    /**
     * createUserAccountPartner method inserts a new user account into the Database
     *
     * @param username
     * @param password
     */

    public void createUserAccountPartner(String username, String password ) {
        String sql = "INSERT INTO [tblUserAccount] VALUES ('"+ username + "', '"+ password +"', '2', '0')";
        db.createPartner(sql);

    }

    /**
     * getOrder method gets info about order from the DB class
     */

    public void getOrder(){
        orderInfo = db.getOrder();
    }

    /**
     * updateDBPartnerName method updates a new updated partner name
     *
     * @param partnerID
     * @param name
     */

    public void updateDBPartnerName(String partnerID, String name) {
        String sql = "UPDATE tblPartner SET fldName = '"+ name + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBPartnerPhoneNo method updates a new updated partner phone number
     *
     * @param partnerID
     * @param phoneNo
     */

    public void updateDBPartnerPhoneNo(String partnerID, String phoneNo) {
        String sql = "UPDATE tblPartner SET fldPhone_no = '"+ phoneNo + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBPartnerEmail method updates a new updated partner email
     *
     * @param partnerID
     * @param email
     */

    public void updateDBPartnerEmail(String partnerID, String email) {
        String sql = "UPDATE tblPartner SET fldEmail = '"+ email + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBPartnerAddress method updates a new updated partner address
     *
     * @param partnerID
     * @param address
     */

    public void updateDBPartnerAddress(String partnerID, String address) {
        String sql = "UPDATE tblPartner SET fldAddress = '"+ address + "'  WHERE fldPartner_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBDriverName method updates a new updated driver name
     *
     * @param partnerID
     * @param name
     */

    public void updateDBDriverName(String partnerID, String name) {
        String sql = "UPDATE tblDrivers SET fldName = '"+ name + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBDriverPhoneNo method updates a new updated driver phone number
     * @param partnerID
     * @param phoneNo
     */

    public void updateDBDriverPhoneNo(String partnerID, String phoneNo) {
        String sql = "UPDATE tblDrivers SET fldPhone_no = '"+ phoneNo + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBDriverEmail method updates a new updated driver email
     *
     * @param partnerID
     * @param email
     */

    public void updateDBDriverEmail(String partnerID, String email) {
        String sql = "UPDATE tblDrivers SET fldEmail = '"+ email + "'  WHERE fldDriver_id = '"+ partnerID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBClothingType method updates a new updated clothing/laundry type
     * @param clothingID
     * @param type
     */

    public void updateDBClothingType(String clothingID, String type) {
        String sql = "UPDATE tblLaundry SET fldType = '"+ type + "'  WHERE fldLaundry_id = '"+ clothingID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * updateDBClothingPrice method updates a new updated clothing/laundry price
     *
     * @param clothingID
     * @param price
     */

    public void updateDBClothingPrice(String clothingID, String price) {
        String sql = "UPDATE tblLaundry SET fldPrice = '"+ price+ "'  WHERE fldLaundry_id = '"+ clothingID +"'";
        db.updateDBInfo(sql);
    }

    /**
     * createDriver method creates a new driver into the database, and inserts the driver login into the user account table in the database
     *
     * @param name
     * @param email
     * @param phone
     * @param password
     */

    public void createDriver(String name, String email, String phone, String password) {
        String sql = "INSERT INTO tblDrivers VALUES ('"+ name + "', '"+ phone +"', '" + email+ "')";
        String sql1 = "INSERT INTO tblUserAccount VALUES ('"+ email + "', '"+ password +"', '3', '0')";
        db.genericConnection(sql);
        db.genericConnection(sql1);

        driverArrayList = db.getDrivers();
    }
}
