package Controller;

import Service.BuyerService;
import exception.BuyerFoundException;

public class BuyerController {

    //singleton pattern
    private static BuyerController BuyerControllerObject = new BuyerController();
    private BuyerController() {
    }

    public static BuyerController getBuyerControllerObject() {
        return BuyerControllerObject;
    }

    public void addBuyer(String name){
        try {
            BuyerService.getBuyerServiceObject().addBuyerService(name);
        } catch (BuyerFoundException e) {
            e.printStackTrace();
        }
    }
}
