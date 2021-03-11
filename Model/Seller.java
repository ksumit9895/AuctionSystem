package Model;

public class Seller extends Person{
    double profitLoss;

    public Seller(String name) {
        super(name);
    }

    public double getProfitLoss(){
        return this.profitLoss;
    }

    public void addProfitLoss(double amount) {
        this.profitLoss += amount;
    }
}
