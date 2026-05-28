package sheet_4.mySocket;
import sheet_3.Stocks;
import java.util.List;

import java.io.*;
import java.net.*;

public class FundProxy{
    private String name;
    private int portNumber;

    public FundProxy(String name, int portNumber){
        this.name = name;
        this.portNumber = portNumber;
    }

    public Stocks getStockByName(String stockName){
        Message message = new Message(this.name, "getStockByName", new Object[]{stockName});
        return sendGetStockByNameRequest(message);
    }

    public void addStock(String stockName){
        Message message = new Message(this.name, "addStock", new Object[]{stockName});
        sendAddStockRequest(message);
    }

    public List<Stocks> getStocks(){
        Message message = new Message(this.name, "getStocks", new Object[]{});
        return this.sendGetListOfStocksRequest(message);
    }

    private Stocks sendGetStockByNameRequest(Message message){
        try{
            Socket socket= new Socket("localhost", this.portNumber);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(message);
            Stocks stock = (Stocks) in.readObject();
            socket.close();
            return stock;
        }catch(IOException e){System.out.println(e.getMessage()); 
        }catch(ClassNotFoundException e){System.out.println(e.getMessage());
        }
        return null;
    }
    private void sendAddStockRequest(Message message){
        try{
            Socket socket= new Socket("localhost", this.portNumber);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message);
            socket.close();
            System.out.println("Stock added succesfully to fund: "+this.name+" \n\n");
        }catch(IOException e){System.out.println(e.getMessage()); 
        }
    }
    private List<Stocks> sendGetListOfStocksRequest(Message message){
        try{
            Socket socket = new Socket("localhost", this.portNumber);
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
            out.writeObject(message);
            List<Stocks> readList= (List<Stocks>) in.readObject();
            System.out.println("List from: "+this.name+" read succesfully\n");
            System.out.println("\n"+readList.toString()+"\n\n");
            socket.close();
            return readList;
        }catch(IOException e){System.out.println(e.getMessage());}
        catch(ClassNotFoundException e){System.out.println(e.getMessage());
        }
        return null;
    }
    
}