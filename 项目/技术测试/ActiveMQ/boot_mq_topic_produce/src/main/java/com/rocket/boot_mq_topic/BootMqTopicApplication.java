package com.rocket.boot_mq_topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootMqTopicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqTopicApplication.class, args);
    }

}
