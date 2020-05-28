package Domain;

import java.sql.*;

public  class UserAccount {
    /*private String username;
    private String password;
    private String name;
    private String phoneNo;*/

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    String userEmail;
    String userPassword;

    public void selectUser(String sql){

        String url = "jdbc:sqlserver://LAPTOP-KLTD07BQ\\SQLEXPRESS;databaseName=Dry_Cleaning_Service";
        String user = "sa";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                userEmail = result.getString("fldEmail");
                //userPassword = result.getString("fldPassword");

                //System.out.println(userEmail + " " + userPassword);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
