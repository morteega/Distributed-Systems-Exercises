package sheet_4.mySocket;

import sheet_3.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer {
    
    public static void main(String []args){
        Funds fund1 = new Funds("Fund1", new ArrayList<Stocks>());
        Funds fund2 = new Funds("Fund2", new ArrayList<Stocks>());
        fund1.addStock("Nvidia", 15, 80);
        fund1.addStock("MSCI", 7, 120);
        fund1.addStock("S&P500", 4, 146);
        fund2.addStock("Tesla", 6,39);
        fund2.addStock("Apple", 3, 50);
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server is listening on port " + serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Connection(clientSocket, fund1, fund2).run();
            }
        }catch(IOException e){
            System.out.println("Listen :"+e.getMessage());
        }
    }




}
