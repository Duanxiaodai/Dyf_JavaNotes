package com.rocket.springboot_activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @ClassName producer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Component
public class Queue_Produce {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"******:"+ UUID.randomUUID().toString().substring(0,6));
    }
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"******Scheduled:"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("发送消息。。。");
    }
}
