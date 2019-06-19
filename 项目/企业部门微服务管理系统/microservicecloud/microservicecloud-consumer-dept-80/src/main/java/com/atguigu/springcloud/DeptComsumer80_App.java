package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 *@Description TODO
 *@Author  DuanYueFeng
 *@Version  1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration= MySelfRule.class)
public class DeptComsumer80_App
{
    public static void main( String[] args )
    {
        SpringApplication.run(DeptComsumer80_App.class,args);
    }
}
