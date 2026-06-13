

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Funds fund = new Funds("Fund1", new ArrayList<Stocks>());
        fund.addStock("Nvidia", 15, 80);
        fund.addStock("MSCI", 7, 120);
        fund.addStock("S&P500", 4, 146);
        fund.addStock("Tesla", 6,39);
        fund.addStock("Apple", 3, 50);

        System.out.println(fund.getStockFromFileByName("sheet_3/fund_old_version.ser","MSCI" ).toString()+"\n\n");

        //fund.saveFundToFile("fund.ser");
        Funds loadedFund = fund.loadFundFromFile("sheet_3/fund_stocks_with_market.ser");
        System.out.println("\n Loaded Fund:\n"+ loadedFund.getName()+ "\n"+ loadedFund.getStocks().toString());
        System.out.println("\n \nNumber of stocks with dividend over 5: " + calculateNumberStocksDividendOver5(fund)+"\n");
        System.out.println("Number of all separate stocks with dividend over 5: " + calculateNumberallSeparateStocksDividendOver5(fund) + "\n");
        getStockByName(fund, "Tesla");
    }




    private static double calculateNumberStocksDividendOver5(Funds fund){
        double count=0;
        for(int i=0; i<fund.getStocks().size(); i++){
            if(fund.getStocks().get(i).getDividend()>5){
                count++;
            }
        }
        return count;
    }

    private static double calculateNumberallSeparateStocksDividendOver5(Funds fund){
        double count=0;
        List<Stocks> stocks= new ArrayList<Stocks>(fund.getStocks());
        for(int i=0; i< stocks.size(); i++){
            if(stocks.get(i).getDividend()>5){
                count+=stocks.get(i).getQuantity();
            }
        }
        return count;
    }

    private static void getStockByName(Funds fund, String name){
        Stocks stock= fund.getStockByName(name);
        if(stock!=null){
            System.out.println("Name: " + stock.getName() + " Dividend: " + stock.getDividend() + " Quantity: " + stock.getQuantity());
        }
        else{
            System.out.println("Stock with name " + name + " not found");
        }
    }
    
}
