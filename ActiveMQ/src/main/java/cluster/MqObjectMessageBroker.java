package cluster;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MqObjectMessageBroker implements MessageListener {
	@SuppressWarnings("unused")
	private static final transient Logger log = LoggerFactory.getLogger(MqObjectMessageBroker.class);

	private JmsTemplate jmsTemplate;
	
	private String username;
	private String password;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendObject(final MqObjectMessage obj) throws Exception {
		try {
			jmsTemplate.send(new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					Message msg = session.createObjectMessage(obj);
					//消息必须有人消费,否则即使长时间没人消费也不允许中间件删除
					msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
					session.getAcknowledgeMode();
					return msg;
				}
			});
		} catch (Exception e) {
			/*log.error(Msg.t("MQ服务器异常", M.MqObjectMessageBroker000), e);
			throw new Exception(Msg.t("MQ服务器异常", M.MqObjectMessageBroker001), e);*/
		}
		
	}

	public void onMessage(Message paramMessage) {

		ObjectMessage objMsg = (ObjectMessage) paramMessage;
	//	Object obj = objMsg.getObject();
		
		/*if (obj instanceof MqObjectMessage) {
			MqObjectMessage msg = (MqObjectMessage)obj;
		}*/

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
