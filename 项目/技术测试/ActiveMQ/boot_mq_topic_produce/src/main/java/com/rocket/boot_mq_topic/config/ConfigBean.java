package com.rocket.boot_mq_topic.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @ClassName ConfigBean
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Component
@EnableJms
public class ConfigBean {
    @Value("${mytopic}")
    private String myTopic;

    @Bean
    public Topic queue(){
        return new ActiveMQTopic(myTopic);
    }
}
