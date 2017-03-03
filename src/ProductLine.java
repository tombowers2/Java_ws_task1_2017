/**
 * Created by Tom on 07/02/2017.
 */
public class ProductLine {
    //variable declaration
    private int stock;
    private int cost;
    private final String NAME;
    //constructor
    public ProductLine(int stock, int cost, String name) {
        //refers to variable in specific instance
        this.stock = stock;
        this.cost = cost;
        NAME = name;
    }
    //getter and setter methods
    public int getStock() {
        return stock;
    }

    public int getCost() {
        return cost;
    }

    public String getNAME() {
        return NAME;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
