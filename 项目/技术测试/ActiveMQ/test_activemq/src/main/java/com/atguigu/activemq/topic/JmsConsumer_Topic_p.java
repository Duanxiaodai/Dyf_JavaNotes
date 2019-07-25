package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.test.TestComponent;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsConsumer_Topic_p {
    public static final String URL = "tcp://10.112.70.211:61616";
    public static final String topic_name = "topic_p";

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        System.out.println("我是3号消费者。。。");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.setClientID("z3");
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topic_name);
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark..");

        connection.start();

        Message message = durableSubscriber.receive();

        while (null!=message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("收到的持久化："+textMessage.getText());
            message = durableSubscriber.receive(1000L);
        }

//        Thread.sleep(1000);
        session.close();
        connection.close();
        System.out.println("完成....");
    }
}
