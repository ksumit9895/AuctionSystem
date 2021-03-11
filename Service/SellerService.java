package Service;

import Model.Seller;
import Repository.SellerRepo;
import exception.SellerFoundException;
import exception.sellerNotFoundException;

public class SellerService {
    //singleton pattern
    private static SellerService SellerServiceObject = new SellerService();
    private SellerService() { }

    public static SellerService getSellerServiceObject() {
        return SellerServiceObject;
    }

    public void addSellerService(String name) throws SellerFoundException {
        if(SellerRepo.getSellerRepoObject().getSeller(name) != null){
            throw new SellerFoundException("Buyer is already Added!!!");
        }
        SellerRepo.getSellerRepoObject().addSellerToSellers(name);
    }

    Seller getSellerObject(String name) throws sellerNotFoundException {
        // check for seller
        Seller seller = SellerRepo
                .getSellerRepoObject()
                .getSeller(name);
        if(seller == null)
            throw new sellerNotFoundException("Seller doesn't Exist with provided name. Add Seller first!!!");
        return seller;
    }

    public double getProfitLossService(String sellerName) throws sellerNotFoundException {
        //check for seller.
        Seller seller = getSellerObject(sellerName);
        return SellerRepo.getSellerRepoObject().getProfitLossRepo(seller);
    }
}
