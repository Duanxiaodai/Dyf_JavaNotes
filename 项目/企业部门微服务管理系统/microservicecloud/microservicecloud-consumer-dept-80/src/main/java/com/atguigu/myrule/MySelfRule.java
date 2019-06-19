package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Description TODO
 *@Author  DuanYueFeng
 *@Version  1.0
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
//        return new RandomRule();
        return new RandomRule_DYF();
    }
}
