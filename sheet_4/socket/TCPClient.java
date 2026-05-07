
import java.net.*;

import sheet_4.mySocket.FundProxy;

import java.io.*;

public class TCPClient {
	 FundProxy fundProxy;
  public static void main (String args[]) {
  // args[0]: Message
  // args[1]: Server
  
    try{
	  int serverPort = 7896;
	  Socket s = new Socket (args[1], serverPort);
	  ObjectOutputStream out = new ObjectOutputStream ( s.getOutputStream());
	  ObjectInputStream in = new ObjectInputStream ( s.getInputStream());

	  Object data = in.readObject();
	  System. out.println("Received: "+ data) ;
	  s.close();
    }catch (UnknownHostException e){
	  System.out.println(" Sock:"+ e.getMessage());
    }catch (EOFException e){ System.out.println(" EOF:"+ e.getMessage());
    }catch (IOException e){ System.out.println(" IO:"+ e.getMessage());
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
  }// main
}// class