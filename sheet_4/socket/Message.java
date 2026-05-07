import java.io.*;

public class Message implements Serializable {
    String fundId;
    String method;
    private String 

    public Message(String fundName, String method){
        this.fundId=fundName;
        this.method=method;
    }
    public String getFundName(){
        return this.fundId;
    }
    public String getMethod(){
        return this.method;
    }
    
}
