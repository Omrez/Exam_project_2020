package Domain;

import Service.DB;

import java.sql.*;

public class Clothing {

    String type;
    String price;


    public Clothing(String type,String price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }





}
