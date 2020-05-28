package Application;

import Domain.Clothing;
import Service.DB;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class Controller {



    public void initialize(){

        DB db = new DB();
        db.getLaundry("select fldType,fldPrice from tblLaundryPrice where fldLaundryPrice_id ='1'");
        Clothing jeans = new Clothing(db.getType(), db.getPrice());

        System.out.println(jeans);


    }



}
