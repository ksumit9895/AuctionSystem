package Service;

import Model.*;
import Repository.AuctionRepo;
import Repository.BidRepo;
import Repository.SellerRepo;
import exception.auctionFoundException;
import exception.auctionNotFoundException;
import exception.sellerNotFoundException;

public class AuctionService {
    //singleton pattern
    private static AuctionService AuctionServiceObject = new AuctionService();
    private AuctionService() { }

    public static AuctionService getAuctionServiceObject() {
        return AuctionServiceObject;
    }

    public void createAuctionService(String id, int lowestLimit, int highestLimit, int participationCost, String name) throws Exception {
        checkExistingAuctionService(id);
        Seller seller = SellerService
                                .getSellerServiceObject()
                                .getSellerObject(name);
        // create auction

        AuctionRepo
                .getAuctionRepoObject()
                .createAuctionRepo(id,lowestLimit,highestLimit,participationCost,seller);
    }

    void checkExistingAuctionService(String id) throws auctionFoundException {
        // check for auction
        if(getAuctionService(id) != null)
            throw new auctionFoundException("Auction Exist with provided name");
    }

    Auction getAuctionService(String id){
        return AuctionRepo
                .getAuctionRepoObject()
                .getAuction(id);
    }

    public void validateAuctionService(String auctionId) throws auctionNotFoundException {
        Auction auction = getAuctionService(auctionId);
        if(auction == null || auction.getState() == STATE.CLOSED)
            throw new auctionNotFoundException("Auction Exist with provided name");
    }

    public String closeAuctionService(String auctionId) throws auctionNotFoundException {
        validateAuctionService(auctionId);
        Auction auction = getAuctionService(auctionId);
        Bid winningBid = getWinningBid(auction);
        AuctionRepo.getAuctionRepoObject().closeAuctionRepo(auction);
        if(winningBid != null) {
            AuctionRepo.getAuctionRepoObject().addProfitLoss(auction, winningBid);
            return winningBid.getBuyer().getName();
        }
        else
            return null;
    }

    private Bid getWinningBid(Auction auction) {
        return BidService.getBidServiceObject().getWinningBid(auction);
    }
}
