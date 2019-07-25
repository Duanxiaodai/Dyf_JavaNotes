package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @ClassName JmsConsumer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
public class JmsConsumer {
    public static final String URL = "nio://10.112.70.211:61616";
    public static final String queue_name = "Transport";

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(true,Session.DUPS_OK_ACKNOWLEDGE);
        Queue queue = session.createQueue(queue_name);
        MessageConsumer consumer = session.createConsumer(queue);
        while (true){
            TextMessage receive = (TextMessage) consumer.receive(1000);
            if (null != receive){
                System.out.println("消息:"+receive.getText());
            }
            else {
                System.out.println("结束");
                break;
            }
        }

//        System.in.read();
//        Thread.sleep(1000);
        consumer.close();
        session.commit();
        session.close();
        connection.close();
        System.out.println("完成....");
    }
}
