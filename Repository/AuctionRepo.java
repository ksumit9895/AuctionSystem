package Repository;
import Model.*;

import java.util.HashMap;
import java.util.Map;

public class AuctionRepo {
    //DB items
    Map<String, Auction> auctions;

    //Singleton Implementation
    private AuctionRepo() {
        this.auctions = new HashMap<>();
    }
    public static AuctionRepo auctionRepoObject = new AuctionRepo();
    public static AuctionRepo getAuctionRepoObject(){
        return auctionRepoObject;
    }

    public Auction getAuction(String name){
        return auctions.getOrDefault(name, null);
    }

    public void createAuctionRepo(String id, int lowestLimit, int highestLimit, int participationCost, Seller seller){
        Auction auction = new Auction(id, lowestLimit, highestLimit, participationCost, seller);
        auctions.put(id, auction);
    }

    public void closeAuctionRepo(Auction auction) {
        auction.setStateClose();
    }

    public void addProfitLoss(Auction auction, Bid bid) {
        double amount = bid.getAmount() - auction.getParticipationCost()
                - auction.getTotalParticipation()* 0.2* auction.getParticipationCost()
                - (auction.getLowestLimit() + auction.getHighestLimit() )/2.0;
        auction.getSeller().addProfitLoss(amount);
    }

}
