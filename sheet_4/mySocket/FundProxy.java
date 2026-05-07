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

    public Stocks getStockByName(String name){
        Message message = new Message(this.name, "getStockByName");
        return sendRequest(message);
    }

    public void addStock(){
        Message message = new Message(this.name, "addStock");
        sendRequest(message);
    }

    public List<Stocks> getStocks(){
        Message message = new Message(this.name, "getStocks");
        return sendRequestList(message);
    }

    private Stocks sendRequest(Message message){
        try{
            Socket socket= new Socket("localhost", this.portNumber);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(message);
            Stocks stock = (Stocks) in.readObject();
            socket.close();
            return stock;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    private List<Stocks> sendRequestList(Message message){
        try{
            Socket socket= new Socket("localhost", this.portNumber);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(message);
            List<Stocks> stocks = (List<Stocks>)in.readObject();
            socket.close();
            return stocks;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}