package sheet_4.mySocket;

import sheet_3.*;
import java.io.*;
import java.net.*;

public class Connection extends Thread{
    private Socket clientSocket;
    private Funds fund1;
    private Funds fund2;

    public Connection(Socket clientSocket, Funds fund1, Funds fund2){
        this.clientSocket = clientSocket;
        this.fund1 = fund1;
        this.fund2 = fund2;
    }

    public void run(){
        try{
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            Message message = (Message) in.readObject();
            switch(message.getMethod()){
                case "getStockByName":
                    if(message.getFundName().equals(fund1.getName())){
                        out.writeObject(fund1.getStockByName((String) message.getParameters()[0]));
                        out.flush();
                        System.out.println("It is working \n"+fund1.getStockByName((String) message.getParameters()[0]));
                    }else if(message.getFundName().equals(fund2.getName())){
                        out.writeObject(fund2.getStockByName((String) message.getParameters()[0]));
                        out.flush();
                        System.out.println("It is working \n" + fund2.getStockByName((String) message.getParameters()[0]));
                    }else{
                        out.writeObject(null);
                    }
                    break;
                case "addStock":
                    String stockName= (String)message.getParameters()[0];
                    if(message.getFundName().equals(fund1.getName())){
                        fund1.addStock(stockName,100, 0.5);
                    }else if(message.getFundName().equals(fund2.getName())){
                        fund2.addStock(stockName,100, 0.5);
                    }
                    break;    
                case "getStocks":
                    if(message.getFundName().equals(fund1.getName())){
                        out.writeObject(fund1.getStocks());
                    }else if(message.getFundName().equals(fund2.getName())){
                        out.writeObject(fund2.getStocks());
                    }else{
                        out.writeObject(null);
                    }
                    break;
            }
            
            clientSocket.close();
        }catch(IOException e){
            e.getMessage();
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }  
    }
}