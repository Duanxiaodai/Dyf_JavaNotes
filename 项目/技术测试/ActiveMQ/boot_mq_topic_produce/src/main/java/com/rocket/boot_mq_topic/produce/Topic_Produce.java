package com.rocket.boot_mq_topic.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

/**
 * @ClassName producer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Component
public class Topic_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Topic topic;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(topic,"Topic******:"+ UUID.randomUUID().toString().substring(0,6));
    }
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(topic,"Topic******Scheduled:"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("发送消息。。。");
    }
}
