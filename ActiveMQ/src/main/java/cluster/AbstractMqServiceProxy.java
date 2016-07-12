package cluster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMqServiceProxy {
	private static final transient Logger log = LoggerFactory.getLogger(AbstractMqServiceProxy.class);
	
	protected boolean enable;
	private MqObjectMessageBroker mqObjectMessageBroker;
	public AbstractMqServiceProxy() {
		enable = false;
	}
	
	public void init() {
		/*if (Detect.notEmpty(classesEnabledMq)) {
			String[] clzNames = StringUtil.splitByLinebreak(classesEnabledMq);
			String myClzName = this.getClass().getName();
			for (String clzName : clzNames) {
				if (myClzName.contains(clzName)) {
					enable = true;
					break;
				}
			}
		}		*/
	}
	
	public void sendObject(MqObjectMessage message) throws Exception {
		mqObjectMessageBroker.sendObject(message);
	}


	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public MqObjectMessageBroker getMqObjectMessageBroker() {
		return mqObjectMessageBroker;
	}

	public void setMqObjectMessageBroker(MqObjectMessageBroker mqObjectMessageBroker) {
		this.mqObjectMessageBroker = mqObjectMessageBroker;
	}
	
	
}
