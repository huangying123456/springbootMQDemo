package com.mqdemo;

import com.mqdemo.mq.MqConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@SpringBootApplication(scanBasePackages = {
        "com.mqdemo"})
@EnableDiscoveryClient
@ServletComponentScan
@EnableFeignClients(basePackages = { "com.mqdemo"})
public class MqDemoApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MqDemoApplication.class, args);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Autowired
    MqConsumer mqConsumer;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("开始消费");
        mqConsumer.start();
        mqConsumer.onMessage();
    }

}
