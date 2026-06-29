import jakarta.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.ArrayList;

public class SecondarySystem {
    private Funds replicaFund;

    private void receiveMessages(String queueName)throws NamingException, JMSException{
        Context initialContext= new InitialContext();
        QueueConnectionFactory connectionFactory= (QueueConnectionFactory)initialContext.lookup("ConnectionFactory");
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
        this.replicaFund.addStock(separatedMessage[0],Double.parseDouble(separatedMessage[1]), Double.parseDouble(separatedMessage[1]));
    }
    
}
