package com.atguigu.activemq.embed;

import org.apache.activemq.broker.BrokerService;

/**
 * @ClassName EmbedBroker
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
