package Application;

import Domain.Clothing;
import Service.DB;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class Controller {

    Clothing jeans;
    Clothing jacket;
    DB db;



    public void initialize(){

        db = new DB();
        db.getLaundry("select fldType,fldPrice from tblLaundryPrice where fldLaundryPrice_id ='1'");
        db.getLaundry("select fldType,fldPrice from tblLaundryPrice where fldLaundryPrice_id ='2'");
        jeans = new Clothing(db.getType(), db.getPrice());
        jacket = new Clothing(db.getType(),db.getPrice());

        System.out.println(jeans +""+jacket);


    }



}
