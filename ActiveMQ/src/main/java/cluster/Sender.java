package cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class Sender {

    private JmsTemplate jmsTemplate;
    private Destination destination;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {

		/*try {
            RedeliveryPolicy topicPolicy = new RedeliveryPolicy();
			topicPolicy.setInitialRedeliveryDelay(0);
			topicPolicy.setRedeliveryDelay(1000);
			topicPolicy.setUseExponentialBackOff(false);
			topicPolicy.setMaximumRedeliveries(3);
			Connection  con = jmsTemplate.getConnectionFactory().createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}*/


        System.out.println("---Send:" + text);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                ObjectMessage om = session.createObjectMessage();
                om.setJMSMessageID("111");
                om.setObject(new MqObjectMessage("aaa.class", "revoker"));
                return om;
            }
        });
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-jms-queue-official-service.xml");
        Sender sender = (Sender) ctx.getBean("sender");


        //	JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
        //Destination destination = (Destination) ctx.getBean("destination");
        for (int i = 0; i < 1; i++) {
            final String dd = i + "";
            sender.send(dd);
            System.out.println("---Send:" + dd);
        }
        System.out.println("成功发送了次JMS消息");
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
}