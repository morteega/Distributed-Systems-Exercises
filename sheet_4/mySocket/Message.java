package sheet_4.mySocket;
import java.io.*;

public class Message implements Serializable {
    String fundName;
    String method;

    public Message(String fundName, String method){
        this.fundName=fundName;
        this.method=method;
    }
    public String getFundName(){
        return this.fundName;
    }
    public String getMethod(){
        return this.method;
    }
    
}
