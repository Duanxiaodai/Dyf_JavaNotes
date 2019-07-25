package com.rocket.springboot_activemq;

import com.rocket.springboot_activemq.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootActivemqApplication.class)
@WebAppConfiguration
public class SpringbootActivemqApplicationTests {
    @Autowired
    private Queue_Produce queue_produce;
    @Test
    public void contextLoads() {
        queue_produce.produceMsg();
    }

}
