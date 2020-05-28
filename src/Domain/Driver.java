package Domain;

import java.sql.*;

public class Driver extends UserAccount {

    String driverName;

    public void selectDriver(String sql){
        String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
        String user = "sa";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                driverName = result.getString("fldName");
                //jeansPrice = result.getString("fldPrice");

                System.out.println(driverName);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
