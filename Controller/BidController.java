package Controller;

import Service.BidService;
import exception.*;

public class BidController {
    //singleton pattern
    private static BidController bidControllerObject = new BidController();
    private BidController() {
    }

    public static BidController getBidControllerObject() {
        return bidControllerObject;
    }

    public void createBid(String name, String id, int amount){
        try {
            BidService
                    .getBidServiceObject()
                    .createBidService(name, id, amount);
        } catch (auctionNotFoundException | BuyerNotFoundException | bidFoundException | illegalBidAmountException e) {
            e.printStackTrace();
        }
    }

    public void updateBid(String name, String id, int amount){
        try {
            BidService
                    .getBidServiceObject()
                    .updateBidService(name, id, amount);
        } catch (auctionNotFoundException | BuyerNotFoundException | bidNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void withdrawBid(String name, String id){
        try {
            BidService
                    .getBidServiceObject()
                    .withdrawBidService(name, id);
        } catch (auctionNotFoundException | BuyerNotFoundException | bidNotFoundException e) {
            e.printStackTrace();
        }
    }
}
