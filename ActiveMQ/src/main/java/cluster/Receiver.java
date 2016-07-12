package cluster;

import javax.jms.*;

public class Receiver implements MessageListener {
    // 接听接收
    public void onMessage(Message message) {
        System.out.println("收到信息...");
        if (message instanceof TextMessage) {
            TextMessage text = (TextMessage) message;
            try {
                System.out.println("Receive:" + text.getText());
                Thread.currentThread().sleep(10000);
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //org.springframework.jms.listener.DefaultMessageListenerContainer
        } else {
            ObjectMessage om = (ObjectMessage) message;
            try {
                System.out.println(om.getJMSMessageID());
                System.out.println(om.getJMSMessageID());
                MqObjectMessage mom = (MqObjectMessage) om.getObject();
                System.out.println(mom.getClassName());
                System.out.println(mom.getMethodName());
                message.acknowledge();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("aaaaaa");
    }

    // 手动接收
    /*public static void main(String[] args) throws JMSException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/applicationContext.xml");
		JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate");
		Destination destination = (Destination) ctx.getBean("destination");
		while (true) {
			TextMessage txtmsg = (TextMessage) template.receive(destination);
			if (null != txtmsg)
				System.out.println("收到消息内容为: " + txtmsg.getText());
			else
				break;
		}
	}*/
}