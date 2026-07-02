import jakarta.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;

public class SecondarySystem {
    private Funds replicaFund;

    public SecondarySystem(){
        this.replicaFund=new Funds("replicaFund", new ArrayList<Stocks>());
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
    private void updateStocks(String message){
        String[] separatedMessage=message.split(";");
        if(separatedMessage.length!=3){
            System.out.println("Invalid message recevide\n");
            return;
        }
        this.replicaFund.addStock(separatedMessage[0],Double.parseDouble(separatedMessage[1]), Double.parseDouble(separatedMessage[1]));
    }
    
}
