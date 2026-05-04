package sheet_3;

import java.util.List;

public class Funds {
    private String name;
    private List<Stocks> stocks;

    public Funds(String name, List<Stocks> stocks){
        this.name = name;
        this.stocks = stocks;
    }
    public Stocks getStockByName(String name){
        boolean found = false;
        Stocks stock = this.stocks.get(0);
        for(int i=0; i<this.stocks.size() && !found ;i++){
            if(stock.getName().equals(this.stocks.get(i).getName())){
                found=true;
            }
            stock = this.stocks.get(i);
            }
        return stock;
    }    
    public void addStock(String name, double dividend, double quantity){
        this.stocks.add(new Stocks(name, dividend, quantity));
    }
    public List<Stocks> getStocks() {
        return this.stocks;
    }
    public String getName() {
        return this.name;
    }
}
