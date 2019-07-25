package com.rocket.springboot_activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @ClassName ConfigBean
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myQueue;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
