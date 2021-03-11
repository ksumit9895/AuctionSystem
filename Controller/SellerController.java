package Controller;

import Service.SellerService;
import exception.SellerFoundException;
import exception.sellerNotFoundException;

public class SellerController {

    //singleton pattern
    private static SellerController SellerControllerObject = new SellerController();
    private SellerController() { }

    public static SellerController getSellerControllerObject() {
        return SellerControllerObject;
    }

    public void addSeller(String name){
        try {
            SellerService.getSellerServiceObject().addSellerService(name);
        } catch (SellerFoundException e) {
            e.printStackTrace();
        }
    }
    public void getProfitLoss(String sellerName){
        try {
            System.out.println( "Profit / Loss of Seller " + sellerName + " is "
                    + SellerService.getSellerServiceObject().getProfitLossService(sellerName));
        } catch (sellerNotFoundException e) {
            e.printStackTrace();
        }
    }
}
