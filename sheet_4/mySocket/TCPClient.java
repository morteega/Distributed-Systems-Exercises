package sheet_4.mySocket;

import java.net.*;
import java.io.*;

public class TCPClient {
     FundProxy fundProxy;
    public static void main (String args[]) {
        
        TCPClient client = new TCPClient();
        client.fundProxy = new FundProxy("Fund1", 7896);
        System.out.println(client.fundProxy.getStockByName("Nvidia"));
        client.fundProxy.addStock();
        System.out.println(client.fundProxy.getStocks());
    }
}
