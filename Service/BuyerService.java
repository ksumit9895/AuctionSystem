package Service;

import Model.Buyer;
import Repository.BuyerRepo;
import exception.BuyerFoundException;
import exception.BuyerNotFoundException;

import java.util.HashMap;

public class BuyerService {
    //singleton pattern
    private static BuyerService BuyerServiceObject = new BuyerService();
    private BuyerService() { }

    public static BuyerService getBuyerServiceObject() {
        return BuyerServiceObject;
    }

    public void addBuyerService(String name) throws BuyerFoundException {
        checkBuyer(name);
        BuyerRepo
                .getBuyerRepoObject()
                .addBuyerToBuyers(name);
    }

    public void checkBuyer(String name) throws BuyerFoundException {
        if(BuyerRepo.getBuyerRepoObject().getBuyer(name) != null){
            throw new BuyerFoundException("Buyer is already Added!!!");
        }
    }

    public void validateBuyer(String name) throws BuyerNotFoundException {
        if(BuyerRepo.getBuyerRepoObject().getBuyer(name) == null){
            throw new BuyerNotFoundException("Buyer not found. Add buyer first!!!");
        }
    }

    public Buyer getBuyer(String buyerName) {
        return BuyerRepo
                .getBuyerRepoObject()
                .getBuyer(buyerName);
    }
}
