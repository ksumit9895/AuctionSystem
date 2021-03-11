package Model;

public class Bid implements Comparable<Bid>{
    Buyer buyer;
    Auction auctionId;
    int amount;

    public Bid(Buyer buyerName, Auction auctionId, int amount) {
        this.buyer = buyerName;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int compareTo(Bid next){
        if(this.amount == next.amount)
            if(this.buyer.isPreferred && !next.buyer.isPreferred)
                return -1;
            else if(!this.buyer.isPreferred && next.buyer.isPreferred)
                return 1;
            else
                return 0;
        return (this.amount < next.amount)?  1: -1;
    }

    public Buyer getBuyer() {
        return this.buyer;
    }

    public double getAmount() {
        return this.amount;
    }
}
