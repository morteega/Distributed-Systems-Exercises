import jakarta.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;
import java.util.Scanner;



public class PrimarySystem {
    private Funds fund;

    public PrimarySystem(){
        this.fund=new Funds("MainFund", new ArrayList<Stocks>());
    }
    public void run()throws NamingException, JMSException{
        this.showMenu();
        int choice=this.request();
        if(choice==1){
            String message=this.chooseStock();
            this.sendMessageToSecondary(message);
        }else if(choice==2){
            this.fund.showStocks();
        }
    }
    
    private void sendMessageToSecondary(String messageText) throws NamingException, JMSException{
        Context context =  new InitialContext(); //para conseguir los recursos con el NamingService, no haria falta si suoiese que ConnectionFactory voy a usar 
        QueueConnectionFactory connectionFactory= (QueueConnectionFactory) context.lookup("ConnectionFactory");
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
        Scanner scanner = new Scanner(System.in);
        int result=scanner.nextInt();
        while(result!=3){
            System.out.println("\n Please enter a valid number: 1, 2, 3");
            result= scanner.nextInt();
        }
        scanner.close();
        return result;
    }
    private String chooseStock(){
        System.out.println("\n Please enter Stock to add with the following format: \n Stock Name;Dividend;Quantity\n");
        Scanner scanner= new Scanner(System.in);
        String stocktoAdd=scanner.nextLine();
        scanner.close();
        return stocktoAdd;
    }
}
