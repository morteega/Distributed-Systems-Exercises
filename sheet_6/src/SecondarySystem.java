import java.util.ArrayList;
import java.util.Scanner;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.*;

public class SecondarySystem implements MessageListener{
    private Funds replicaFund;
    private Scanner scanner;

    public SecondarySystem(){
        this.replicaFund= new Funds("replicaFund",new ArrayList<Stocks>());
        this.scanner=new Scanner(System.in);
    }

    public void run(String queueName){  
            try{
                this.receiveMessage(queueName);
            }catch(Exception e){
                e.printStackTrace();
            }
        
    }

    public void receiveMessage(String queueName) throws JMSException{
        QueueConnectionFactory connectionFactory= new ActiveMQConnectionFactory("tcp://localhost:61616");
        QueueConnection connection = connectionFactory.createQueueConnection();
        QueueSession session=connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue=session.createQueue(queueName);
        MessageConsumer consumer=session.createConsumer(queue);
        consumer.setMessageListener(this);
        connection.start();
        this.showMenu();
        this.processRequest(this.request()); 
        }
    private void updateStocks(TextMessage textMessage)throws JMSException{
        String text= textMessage.getText();
        String[] stockToAdd=text.split(";");
        if(stockToAdd.length!=3){
            System.out.println("\nInvalid Message Received");
        }else{
            this.replicaFund.addStock(stockToAdd[0],Double.parseDouble(stockToAdd[1]),Double.parseDouble(stockToAdd[2]));
        }
    }
    @Override
    public void onMessage(Message message){
        try{
            if(message instanceof TextMessage){
            TextMessage textMessage= (TextMessage) message;
            this.updateStocks(textMessage);
            this.replicaFund.showStocks();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
}