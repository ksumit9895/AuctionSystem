package Repository;

import Model.Auction;
import Model.Bid;
import Model.Buyer;

import java.util.*;

public class BidRepo {
    //DB items
    Map<String, Bid> BuyerBids; // key : AuctionName + Buyer
    Map<Auction, PriorityQueue<Bid>> AuctionBids;

    //Singleton Implementation
    private static BidRepo bidRepoObject = new BidRepo();
    private BidRepo() {
        this.BuyerBids = new HashMap<>();
        this.AuctionBids = new HashMap<>();
    }

    public static BidRepo getBidRepoObject() {
        return bidRepoObject;
    }

    public Bid findExistingBidByName(String name) {
        return BuyerBids.getOrDefault(name, null);
    }

    public Bid getMaxUniqueBid(Auction auction){
        PriorityQueue<Bid> bids = AuctionBids.get(auction);
        Bid curr, next;
        while(!bids.isEmpty()){
            curr = bids.poll();
            if(bids.isEmpty()) return curr;
            next = bids.poll();

            if(curr.compareTo(next) == 0){
                while(curr.compareTo(next) == 0){
                    next = bids.poll();
                }
                curr = next;
                if(bids.isEmpty()) return curr;
                next = bids.poll();
            }
            else{
                return curr;
            }
        }
        return null;
    }

    public void createBidRepo(Buyer buyer, Auction auction, int amount) {
        Bid bid = new Bid(buyer, auction, amount);
        BuyerBids.put(buyer.getName().concat(auction.getId()), bid);
        if( !AuctionBids.containsKey(auction))
            AuctionBids.put(auction, new PriorityQueue<>());
        AuctionBids.get(auction).add(bid);
        auction.addParticipation();
    }

    public void updateBidRepo(Buyer buyer, Auction auction, int amount) {
        //fetch previous bid & update details.
        Bid bid = BuyerBids.get(buyer.getName().concat(auction.getId()));
        bid.setAmount( amount);
        AuctionBids.get(auction).add(bid);
    }

    public void withdrawBidRepo(Buyer buyer, Auction auction) {
        Bid bid = BuyerBids.get(buyer.getName().concat(auction.getId()));
        AuctionBids.get(auction).remove(bid);
        auction.removeParticipation();
    }
}
