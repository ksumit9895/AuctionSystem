package Controller;

import Service.AuctionService;
import exception.auctionNotFoundException;

public class AuctionController {
    //singleton pattern
    private static AuctionController AuctionControllerObject = new AuctionController();
    private AuctionController() {
    }

    public static AuctionController getAuctionControllerObject() {
        return AuctionControllerObject;
    }

    public void createAuction(String id, int lowestLimit, int highestLimit, int participationCost, String name){
        try {
            AuctionService
                    .getAuctionServiceObject()
                    .createAuctionService(id,lowestLimit,highestLimit,participationCost, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAuction(String auctionId){
        String winner;
        try {
            winner = AuctionService.getAuctionServiceObject().closeAuctionService(auctionId);
            if(winner == null)
                System.out.println("No winner for Auction " + auctionId);
            else
                System.out.println("winner for Auction " + auctionId + " is "+ winner);
        } catch (auctionNotFoundException e) {
            e.printStackTrace();
        }
    }
}
