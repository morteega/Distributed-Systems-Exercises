import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.NamingException;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SecondarySystem_4 {
    private Funds replicaFund;
    private Scanner scanner;

    public SecondarySystem_4(){
        this.replicaFund= new Funds("replicaFund",new ArrayList<Stocks>());
        this.scanner=new Scanner(System.in);
    }

    public void run(String queueName){
        while(true){
            try{
                this.receiveMessages(queueName);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void receiveMessages(String queueName)throws NamingException, JMSException{
        QueueConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        QueueConnection connection=connectionFactory.createQueueConnection();
        connection.start();
        QueueSession session= connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue=session.createQueue(queueName);
        MessageConsumer receiver= session.createConsumer(queue);
        Message message=receiver.receive();
        if(message instanceof TextMessage){
            TextMessage textMessage=(TextMessage) message;
            String text=textMessage.getText();
            System.out.println("Message received correctly: "+text);
            this.updateStocks(text);
            this.replicaFund.showStocks();
        }
        connection.close();
        session.close();
    }
    private void showMenu(){
        System.out.println("\n Would you like to view all the stocks in the fund?: \n 1.Yes (1)");
    }
    private int request(){
        int result=scanner.nextInt();
        this.scanner.nextLine();
        return result;
    }
    private void processRequest(int request){
        if(request==1){
            this.replicaFund.showStocks();
        }
    }
    private void updateStocks(String message){
        String[] separatedMessage=message.split(";");
        if(separatedMessage.length!=3){
            System.out.println("Invalid message recevide\n");
            return;
        }
    }
    
}
