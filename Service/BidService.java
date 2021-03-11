package Service;

import Model.Auction;
import Model.Bid;
import Model.Buyer;
import Repository.BidRepo;
import exception.*;

public class BidService {
    //singleton pattern
    private static BidService BidServiceObject = new BidService();
    private BidService() { }

    public static BidService getBidServiceObject() {
        return BidServiceObject;
    }

    public void createBidService(String buyerName, String auctionId, int amount) throws BuyerNotFoundException, bidFoundException, auctionNotFoundException, illegalBidAmountException {
        //check Auction -> auction should exist
        AuctionService
                .getAuctionServiceObject()
                .validateAuctionService(auctionId);
        //check buyer -> buyer should exist
        BuyerService
                .getBuyerServiceObject()
                .validateBuyer(buyerName);
        //check bid -> bid should not exist
        BidService
                .getBidServiceObject()
                .checkBid(buyerName, auctionId);

        Auction auction = AuctionService
                .getAuctionServiceObject()
                .getAuctionService(auctionId);
        Buyer buyer = BuyerService
                .getBuyerServiceObject()
                .getBuyer(buyerName);
        //If bid amount is less than lowestBidLimit
        if(amount < auction.getLowestLimit())
            throw new illegalBidAmountException("Bid Amount less than lowest bid amount");
        //if bid amount is higher than highestBidLimit
        if(amount > auction.getHighestLimit())
            throw new illegalBidAmountException("Bid Amount higher than highest bid amount");
        //createBid
        BidRepo
                .getBidRepoObject()
                .createBidRepo(buyer, auction, amount);
    }

    public void updateBidService(String buyerName, String auctionId, int amount) throws BuyerNotFoundException, bidNotFoundException, auctionNotFoundException {
        //check Auction -> auction should exist
        AuctionService
                .getAuctionServiceObject()
                .validateAuctionService(auctionId);
        //check buyer -> buyer should exist
        BuyerService
                .getBuyerServiceObject()
                .validateBuyer(buyerName);
        //check bid -> bid should exist
        BidService
                .getBidServiceObject()
                .validateBid(buyerName, auctionId);

        Auction auction = AuctionService
                .getAuctionServiceObject()
                .getAuctionService(auctionId);
        Buyer buyer = BuyerService
                .getBuyerServiceObject()
                .getBuyer(buyerName);
        //createBid
        BidRepo
                .getBidRepoObject()
                .updateBidRepo(buyer, auction, amount);
    }

    public void withdrawBidService(String buyerName, String auctionId) throws BuyerNotFoundException, bidNotFoundException, auctionNotFoundException {
        //check Auction -> auction should exist
        AuctionService
                .getAuctionServiceObject()
                .validateAuctionService(auctionId);
        //check buyer -> buyer should exist
        BuyerService
                .getBuyerServiceObject()
                .validateBuyer(buyerName);
        //check bid -> bid should exist
        BidService
                .getBidServiceObject()
                .validateBid(buyerName, auctionId);

        Auction auction = AuctionService
                .getAuctionServiceObject()
                .getAuctionService(auctionId);
        Buyer buyer = BuyerService
                .getBuyerServiceObject()
                .getBuyer(buyerName);
        //createBid
        BidRepo
                .getBidRepoObject()
                .withdrawBidRepo(buyer, auction);
    }

    private void checkBid(String buyerName, String auctionId) throws bidFoundException {
        Bid bid = BidRepo.getBidRepoObject().findExistingBidByName(buyerName + auctionId);
        if(bid != null)
            throw new bidFoundException("Buyer has previously bid in this Auction. use Update Bid instead!!!");
    }

    private void validateBid(String buyerName, String auctionId) throws bidNotFoundException {
        Bid bid = BidRepo.getBidRepoObject().findExistingBidByName(buyerName + auctionId);
        if(bid == null)
            throw new bidNotFoundException("Buyer has not previously bid in this Auction. use Create Bid instead!!!");
    }

    public Bid getWinningBid(Auction auction) {
        return BidRepo.getBidRepoObject().getMaxUniqueBid(auction);
    }
}
