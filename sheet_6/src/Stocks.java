import java.io.*; 

public class Stocks implements Serializable {
    private String name;
    private double dividend;
    private double quantity;
    private static final long serialVersionUID = 1L;

    public Stocks(String name, double dividend, double quantity){
        this.name = name;
        this.dividend = dividend;
        this.quantity = quantity;
        
    }
    public String getName() {
        return name;
    }
    public double getDividend() {
        return dividend;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String toString(){
        return "Stock: " + this.name + ", Dividend: " + this.dividend + ", Quantity: " + this.quantity;
    }
    public void setDividend(double dividend){
        this.dividend=dividend;
    }
}
