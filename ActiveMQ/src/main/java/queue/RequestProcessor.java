package queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RequestProcessor {
    public void requestHandler(HashMap<String, String> requestParam) throws Exception {
        System.out.println("requestHandler....." + requestParam.toString());
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://master:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("RequestQueue");
        //消息消费（接收）者
        MessageConsumer consumer = session.createConsumer(destination);

        RequestProcessor processor = new RequestProcessor();

        while (true) {
            ObjectMessage message = (ObjectMessage) consumer.receive(1000);
            if (null != message) {
                System.out.println(message);
                HashMap<String, String> requestParam = (HashMap<String, String>) message.getObject();
                processor.requestHandler(requestParam);
            } else {
                break;
            }
        }
    }
}