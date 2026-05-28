package sheet_4.mySocket;


public class TCPClient {
    FundProxy fundProxy1;
    FundProxy fundProxy2;
    public static void main (String args[]) {
        TCPClient client=new TCPClient();
        client.fundProxy1=new FundProxy("Fund1", 7896);//Eso es para que se conecte correctamente al puerto del servidor
        client.fundProxy2=new FundProxy("Fund2", 7896 );
        System.out.println("\n" +client.fundProxy1.getStockByName("MSCI")+ "\n");
        System.out.println("\n" +client.fundProxy2.getStockByName("Tesla")+ "\n");
        client.fundProxy1.addStock( "Apple");
        client.fundProxy2.addStock( "Apple");
        client.fundProxy1.getStocks();
        client.fundProxy2.getStocks();
        
    }
}
