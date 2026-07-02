import java.util.ArrayList;
import java.util.Scanner;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.*;

public class PrimarySystem{
    private Funds realFund;
    private Scanner scanner;

    public PrimarySystem(){
        this.realFund=new Funds("realFund", new ArrayList<Stocks>());
        this.scanner=new Scanner(System.in);
    }
    public void run(){
        this.showMenu();
        int result=this.request();
        while(true && result !=3){
            if(result==1){
                try{
                    System.out.println("Please enter the stock you want to add in the following format:\n Name;Dividend;Quantity");
                    String stockToAdd=this.scanner.nextLine();
                    this.sendMessage(stockToAdd);
                    String[] stock=stockToAdd.split(";");
                    this.realFund.addStock(stock[0],Double.parseDouble(stock[1]),Double.parseDouble(stock[1]));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else if(result==2){
                this.realFund.showStocks();
            }
            this.showMenu();
            result=this.request();
        }
    }

    private void sendMessage(String textMessage)throws JMSException{
        QueueConnectionFactory connectionFactory= new ActiveMQConnectionFactory("tcp://localhost:61616");
        QueueConnection connection= connectionFactory.createQueueConnection();
        connection.start();
        QueueSession session= connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue1=session.createQueue("primary_to_secondary_1");
        Queue queue2=session.createQueue("primary_to_secondary_2");
        MessageProducer messageProducer1=session.createProducer(queue1);
        MessageProducer messageProducer2=session.createProducer(queue2);
        TextMessage message=session.createTextMessage(textMessage);
        messageProducer1.send(message);
        messageProducer2.send(message);
        session.close();
        connection.close();
    }
    private void showMenu(){
        System.out.println("\n 1. Add New Stock: \n 2. View All Stocks: \n 3. Exit");
    }
    private int request(){
        int result=this.scanner.nextInt();
        this.scanner.nextLine();
        while(result<1 || result > 3){
            System.out.println("\nPlease enter a valid number: \n");
            result=this.scanner.nextInt();
            this.scanner.nextLine();
        }
        return result;
    }

}