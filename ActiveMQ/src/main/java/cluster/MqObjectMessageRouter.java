package cluster;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqObjectMessageRouter {
	@SuppressWarnings("unused")
	private static final transient Logger log = LoggerFactory.getLogger(MqObjectMessageRouter.class);

	private String username;
	private String password;
	
	
	public void route(Message msg) {
		
		ObjectMessage objMsg = (ObjectMessage) msg;
		
		try {
		} catch (Exception e) {
		}
		
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
