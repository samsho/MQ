package cluster;

import org.apache.activemq.RedeliveryPolicy;

public class DelayCustomize extends RedeliveryPolicy {
    public void init() {
        this.setMaximumRedeliveries(10);
        this.setInitialRedeliveryDelay(1000);
        this.setTempQueue(true);
        this.setQueue("bbbbbbb");
    }

    public DelayCustomize() {

    }
}
