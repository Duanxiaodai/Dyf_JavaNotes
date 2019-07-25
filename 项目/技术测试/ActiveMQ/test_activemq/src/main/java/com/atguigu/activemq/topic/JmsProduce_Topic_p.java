package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName JmsProduce
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsProduce_Topic_p {
    public static final String URL = "tcp://10.112.70.211:61616";
    public static final String topic_name = "topic_p";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topic_name);
        MessageProducer producer = session.createProducer(topic);
//        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        for (int i = 4; i <=6; i++) {
            TextMessage textMessage = session.createTextMessage("Topic mag..." + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("完成....");
    }
}
