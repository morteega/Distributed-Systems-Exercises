
import java.net.*;
import java.io.*;
import sheet_3.Funds;

public class TCPServer {
  public static void main (String args[]) {
    try{
      System.out.println("The Server is running");
	  int serverPort = 7896;
	  ServerSocket listenSocket = new ServerSocket (serverPort);
	  while(true) {
	    Socket clientSocket = listenSocket.accept();
	    System. out.println("New Connection");
	    Connection c = new Connection(clientSocket);
	  }
    } catch( IOException e) {System.out.println(" Listen :"+ e.getMessage());}
  }// main
}//class


class Connection extends Thread {
  ObjectInputStream in;
  ObjectOutputStream out;
  Socket clientSocket;

  public Connection (Socket aClientSocket) {
    try {
      clientSocket = aClientSocket;
      out = new ObjectOutputStream ( clientSocket.getOutputStream() );
      in = new ObjectInputStream ( clientSocket.getInputStream() );     
      this.start();
    } catch( IOException e) {System. out. println(" Connection:"+ e.getMessage());}
  }

  public void run(){
    try {
      Object data = (String) in.readObject ();
	    out.writeObject(data);
	    System.out.println("Sent data: " + data);	  
      } catch( EOFException e) {System.out.println(" EOF:"+ e.getMessage());
      } catch( IOException e) {System.out.println(" IO:"+ e.getMessage());
      } catch (ClassNotFoundException e){System.out.println(" Class not found:"+ e.getMessage());}
    } 
}