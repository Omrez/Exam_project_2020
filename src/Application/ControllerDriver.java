package Application;

import Domain.Order;
import Service.DB;

import java.util.ArrayList;

public class ControllerDriver {
    DB db = new DB();
    public ArrayList<Order> orderInfo;

    public void getOrder(){

        orderInfo = db.getOrder();

    }
}
