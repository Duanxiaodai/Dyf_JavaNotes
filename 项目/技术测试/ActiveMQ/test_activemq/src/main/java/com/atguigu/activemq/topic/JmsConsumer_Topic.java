package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsConsumer_Topic {
    public static final String URL = "tcp://10.112.70.211:61616";
    public static final String topic_name = "topic01";

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        System.out.println("我是3号消费者。。。");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topic_name);
        MessageConsumer consumer = session.createConsumer(topic);
//        while (true){
//            TextMessage receive = (TextMessage) consumer.receive();
//            if (null != receive){
//                System.out.println("消息:"+receive.getText());
//            }
//            else {
//                System.out.println("结束");
//                break;
//            }
//        }
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接收到消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
//        Thread.sleep(1000);
        consumer.close();
        session.close();
        connection.close();
        System.out.println("完成....");
    }
}
