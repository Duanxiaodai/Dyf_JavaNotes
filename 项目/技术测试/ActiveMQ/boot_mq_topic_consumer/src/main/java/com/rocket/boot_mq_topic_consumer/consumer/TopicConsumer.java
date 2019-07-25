package com.rocket.boot_mq_topic_consumer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @ClassName QueueConsumer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Service
public class TopicConsumer {

    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者接受消息："+textMessage.getText());
    }
}
