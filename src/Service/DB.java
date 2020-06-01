package Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
    String user = "sa";
    String password = "root";

    String type;
    String price;

    String orderNumber;
    String orderDate;
    String orderPrice;

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

    public void getLaundry(String sql) {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                type = result.getString("fldType");
                price = result.getString("fldPrice");


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getOrder(String sql){
        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                orderNumber = result.getString("fldOrderNumber");
                orderDate = result.getString("fldDate");
                orderPrice = result.getString("fldPrice");

                List array = new ArrayList();
                array.add(getOrderNumber());

                System.out.println(array);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
