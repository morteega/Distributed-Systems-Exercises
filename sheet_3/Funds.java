
import java.util.List;
import java.io.*;

public class Funds implements Serializable{
    private String name;
    private List<Stocks> stocks;
    private static final long serialVersionUID = 1L;

    public Funds(String name, List<Stocks> stocks){
        this.name = name;
        this.stocks = stocks;
    }
    public Stocks getStockByName(String name){
        for(int i=0; i<this.stocks.size();i++){
            if(this.stocks.get(i).getName().equals(name))
                return this.stocks.get(i);
            }
        return null;
    }    
    public void addStock(String name, double dividend, double quantity){
        if(this.getStockByName(name)!=null){
            this.getStockByName(name).setQuantity(this.getStockByName(name).getQuantity()+quantity);
            this.getStockByName(name).setDividend(dividend);
        }else{
            Stocks stock = new Stocks(name,dividend,quantity);
            this.stocks.add(stock);
        }
    }
    public List<Stocks> getStocks() {
        return this.stocks;
    }
    public String getName() {
        return this.name;
    }
    public String saveFundToFile(String filename){
        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fileOut);
            os.writeObject(this);
            os.close();
            fileOut.close();
            return "Fund saved to file successfully.";
        }catch(IOException e){System.err.println(e.getMessage());
            return "Error saving fund to file.";
        }
        
    }
    public Funds loadFundFromFile(String filename){
        try{
            FileInputStream fs = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fs);
            Funds fund = (Funds) is.readObject();
            System.out.println("Fund loaded succesfully");
            is.close();
            fs.close();
            return fund;
        }catch(FileNotFoundException e){System.err.println(e.getMessage());
            return null;
        }catch(IOException e){System.err.println(e.getMessage());
            return null;
        }catch(ClassNotFoundException e){System.err.println(e.getMessage());
            return null;
        }
    }
    public Stocks getStockFromFileByName(String file, String name){
        try{
            FileInputStream fs = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fs);
            Funds fund = (Funds) in.readObject();
            fs.close();
            in.close();
            return fund.getStockByName(name);
        }catch(FileNotFoundException e){System.out.println(e.getMessage());
            return null;
        }catch(IOException e){System.err.println(e.getMessage());
            return null;
        }catch(ClassNotFoundException e){System.err.println(e.getMessage());
            return null;
        }
    }
    public String toString(){
        String string="";
        for(int i=0; i<this.stocks.size();i++){
            string+=this.stocks.get(i).toString();
        }
        return string;
    }
}
