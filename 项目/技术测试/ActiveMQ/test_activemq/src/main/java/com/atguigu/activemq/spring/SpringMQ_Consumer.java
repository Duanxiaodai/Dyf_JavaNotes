package com.atguigu.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @ClassName SpringMQ_Consumer
 * @Description TODO
 * @Author DuanYueFeng
 * @Version 1.0
 **/
@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) throws JMSException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer consumer = (SpringMQ_Consumer) applicationContext.getBean("springMQ_Consumer");
//        TextMessage receive =(TextMessage) consumer.jmsTemplate.receive();
        String receive =(String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println("消费者："+receive);
    }
}
