package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName JmsProduce
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsProduce_Topic {
    public static final String URL = "tcp://10.112.70.211:61616";
    public static final String topic_name = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topic_name);
        MessageProducer producer = session.createProducer(topic);

        for (int i = 1; i <=3; i++) {
            TextMessage textMessage = session.createTextMessage("Topic mag..." + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("完成....");
    }
}
