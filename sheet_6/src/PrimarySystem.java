import jakarta.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;
import java.util.Scanner;



public class PrimarySystem {
    private Funds fund;
    private Scanner scanner;

    public PrimarySystem(){
        this.fund=new Funds("MainFund", new ArrayList<Stocks>());
        this.scanner = new Scanner(System.in);
    }
    public void run(){
        this.showMenu();
        int choice=this.request();
        while(true && choice!=3){
            if(choice==1){
                try{
                String message=this.chooseStock();
                this.sendMessageToSecondary(message);
                String[] stock=this.splitString(message);
                this.fund.addStock(stock[0],Double.parseDouble(stock[1]),Double.parseDouble(stock[2]));
            }catch(Exception e){
                e.printStackTrace();
            }
            }else if(choice==2){
                this.fund.showStocks();
            }
            this.showMenu();
            choice=this.request();
        }
    }
    
    private void sendMessageToSecondary(String messageText) throws NamingException, JMSException{ //para conseguir los recursos con el NamingService, no haria falta si suoiese que ConnectionFactory voy a usar 
        QueueConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        QueueConnection connection= connectionFactory.createQueueConnection();
        connection.start();
        QueueSession session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue1=session.createQueue("primary_to_secondary_1");
        Queue queue2=session.createQueue("primary_to_secondary_2");
        MessageProducer producer1=session.createProducer(queue1);
        MessageProducer producer2=session.createProducer(queue2);
        TextMessage message=session.createTextMessage(messageText);
        TextMessage message2=session.createTextMessage(messageText);
        producer1.send(message);
        producer2.send(message2);
        session.close();
        connection.close();
    }
    private void showMenu(){
        System.out.println("\n 1. Add new Stock: \n 2. View all Stocks: \n 3. Exit  \n Enter a valid number: 1, 2, 3\n");
    }
    private int request(){
        int result=this.scanner.nextInt();
        this.scanner.nextLine();
        while(result<1 || result >3){
            System.out.println("Please enter a valid number\n");
            result=this.scanner.nextInt();
            scanner.nextLine();
        }
        return result;
    }
    private String chooseStock(){
        System.out.println("\n Please enter Stock to add with the following format: \n Stock Name;Dividend;Quantity\n");
        String stocktoAdd=this.scanner.nextLine();
        return stocktoAdd;
    }
    private String[] splitString(String message){
        String[] result=message.split(";");
        return result;
    }
}
