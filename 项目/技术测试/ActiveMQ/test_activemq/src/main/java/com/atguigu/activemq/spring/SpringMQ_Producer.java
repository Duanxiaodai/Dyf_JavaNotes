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
public class SpringMQ_Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Producer producer = (SpringMQ_Producer) applicationContext.getBean("springMQ_Producer");
        for (int i = 0; i < 1000; i++) {

            producer.jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage("。。。。");
                    return textMessage;
                }
            });
        }
        System.out.println("完成完成完成完完成");
    }
}
