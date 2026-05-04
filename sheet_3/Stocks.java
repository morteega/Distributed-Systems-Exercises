package sheet_3;



public class Stocks {
    private String name;
    private double dividend;
    private double quantity;

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
}
