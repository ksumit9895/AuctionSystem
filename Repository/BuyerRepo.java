package Repository;

import Model.Buyer;

import java.util.HashMap;
import java.util.Map;

public class BuyerRepo {

    //DB items
    Map<String, Buyer> buyers;

    //Singleton Implementation
    private static BuyerRepo buyerRepoObject = new BuyerRepo();
    private BuyerRepo() {
        this.buyers = new HashMap<>();
    }

    public static BuyerRepo getBuyerRepoObject() {
        return buyerRepoObject;
    }

    public Buyer getBuyer(String name){
        return buyers.getOrDefault(name, null);
    }

    public void addBuyerToBuyers(String name) {
        buyers.put(name, new Buyer(name));
    }
}
