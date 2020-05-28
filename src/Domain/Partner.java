package Domain;

import java.sql.*;

public class Partner {
    /*private String partnerName;
    private int partnerID;*/

    public String getPartnerName() {
        return partnerName;
    }

    public String getPartnerAddress() {
        return partnerAddress;
    }

    String partnerName;
    String partnerAddress;

    public void selectPartner(String sql){
        String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
        String user = "sa";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                partnerName = result.getString("fldName");
                partnerAddress = result.getString("fldAddress");

                System.out.println(partnerName+"\n"+ partnerAddress);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
