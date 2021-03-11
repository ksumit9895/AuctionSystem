import Controller.*;

public class Driver {
    public static void main(String[] args){

        //Repository.SellerRepo rep = new Repository.SellerRepo();
        BuyerController buyerController = BuyerController.getBuyerControllerObject();
        SellerController sellerController = SellerController.getSellerControllerObject();
        AuctionController auctionController = AuctionController.getAuctionControllerObject();
        BidController bidController = BidController.getBidControllerObject();
/*                Test Case: 1                                                                         */
        buyerController.addBuyer("buyer1");
        buyerController.addBuyer("buyer2");
        buyerController.addBuyer("buyer3");
        sellerController.addSeller("seller1");

        auctionController.createAuction("A1", 10, 50, 1, "seller1");

        bidController.createBid("buyer1", "A1", 17);
        bidController.createBid("buyer2", "A1", 15);
        bidController.updateBid("buyer2", "A1", 19);
        bidController.createBid("buyer3", "A1", 19);

        auctionController.closeAuction("A1");
        sellerController.getProfitLoss("seller1");

        /*                Test Case: 2                                                                        */
        sellerController.addSeller("seller2");
        auctionController.createAuction("A2", 5, 20, 2, "seller2");
        bidController.createBid("buyer3", "A2", 25);
        bidController.createBid("buyer2", "A2", 5);
        bidController.withdrawBid("buyer2", "A2");
        auctionController.closeAuction("A2");
        sellerController.getProfitLoss("seller2");
    }
}
