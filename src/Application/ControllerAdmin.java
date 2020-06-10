package Application;

import Domain.Clothing;
import Domain.Driver;
import Domain.Order;
import Domain.Partner;
import Service.DB;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class ControllerAdmin {

    public Clothing jeans;
    public Clothing jacket;
    public Clothing shirt;
    public ObservableList<Partner> partnerArrayList;
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
        db.getPartners();
    }

    public void getOrder(){

        orderInfo = db.getOrder();

    }


}
