package Domain;

import java.sql.*;

public class City {

    String cityName;


    public void selectCity(String sql){
        String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
        String user = "sa";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                cityName = result.getString("fldCityName");
                //jeansPrice = result.getString("fldPrice");

                //System.out.println(jeansType + " " + jeansPrice);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
