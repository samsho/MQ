package cluster;

import java.io.Serializable;

public class MqObjectMessage implements Serializable{

	//private RequestMessage requestMessage;
	
	private String methodName;
	
	private String className;
	
/*	public MqObjectMessage(String className, String methodName, RequestMessage requestMessage) {
		//this.requestMessage = requestMessage;
		this.methodName = methodName;
		this.className = className;
	}*/
	public MqObjectMessage(String className, String methodName) {
		//this.requestMessage = requestMessage;
		this.methodName = methodName;
		this.className = className;
	}

	/*public RequestMessage getRequestMessage() {
		return requestMessage;
	}*/

	public String getMethodName() {
		return methodName;
	}

	public String getClassName() {
		return className;
	}

}
