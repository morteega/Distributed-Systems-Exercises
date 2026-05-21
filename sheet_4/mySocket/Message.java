package sheet_4.mySocket;
import java.io.*;

public class Message implements Serializable {
    String fundName;
    String method;
    Object [] parameters;

    public Message(String fundName, String method, Object[] parameters){
        this.fundName=fundName;
        this.method=method;
        this.parameters=parameters;
    }
    public String getFundName(){
        return this.fundName;
    }
    public String getMethod(){
        return this.method;
    }
    public Object[] getParameters(){
        return this.parameters;
    }
}
