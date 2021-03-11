package Model;

public class Buyer extends Person{

    boolean isPreferred;
    int participationCount;

    public Buyer(String name) {
        super(name);
        isPreferred = false;
        participationCount = 0;
    }

}
