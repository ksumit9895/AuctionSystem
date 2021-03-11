package Repository;

import Model.Seller;

import java.util.HashMap;
import java.util.Map;

public class SellerRepo {
    //DB items
    Map<String, Seller> sellers;
    //Singleton Implementation
    private static SellerRepo sellerRepoObject = new SellerRepo();

    private SellerRepo() {
        this.sellers = new HashMap<>();
    }

    public static SellerRepo getSellerRepoObject() {
        return sellerRepoObject;
    }

    public Seller getSeller(String name){
        return sellers.getOrDefault(name, null);
    }

    public void addSellerToSellers(String name) {
        sellers.put(name, new Seller(name));
    }

    public double getProfitLossRepo(Seller seller){
        return seller.getProfitLoss();
    }
}
