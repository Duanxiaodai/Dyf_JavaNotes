package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName JmsProduce
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsProduce {
    public static final String URL = "nio://10.112.70.211:61616";
    public static final String queue_name = "Transport";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queue_name);
        MessageProducer producer = session.createProducer(queue);

        for (int i = 1; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("mag2..." + i);
            producer.send(textMessage);
        }
        producer.close();
        session.commit();
        session.close();
        connection.close();
        System.out.println("....");
    }
}
