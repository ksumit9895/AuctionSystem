package Model;

public class Auction {
    String id;
    int lowestBidLimit;
    int highestBidLimit;
    int participationCost;
    Seller seller;
    STATE state;
    Buyer winner;
    int totalParticipation;

    public Auction(String id, int lowestBidLimit, int highestBidLimit, int participationCost, Seller seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        state = STATE.OPENED;
        winner = null;
        totalParticipation = 0;
    }

    public String getId() {
        return this.id;
    }

    public void setStateClose() {
        this.state = STATE.CLOSED;
    }

    public STATE getState() {
        return this.state;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public int getParticipationCost() {
        return this.participationCost;
    }

    public int getTotalParticipation() {
        return this.totalParticipation;
    }

    public int getLowestLimit() {
        return this.lowestBidLimit;
    }

    public int getHighestLimit() {
        return this.highestBidLimit;
    }

    public void addParticipation() {
        this.totalParticipation += 1;
    }

    public void removeParticipation() {
        this.totalParticipation -= 1;
    }
}
