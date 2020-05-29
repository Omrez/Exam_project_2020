package Service;

import java.sql.*;

public class DB {

    String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
    String user = "sa";
    String password = "root";

    String type;
    String price;


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

                System.out.println(type + " " + price);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
