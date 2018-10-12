package com.mqdemo.mq;

import com.aliyun.openservices.ons.api.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hy on 2018/10/12.
 */
@Component
public class MqConsumer implements InitializingBean,DisposableBean {

    @Autowired
    MqConfig busMqConfig;
    private Consumer busConsumer;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("消费者初始化");
        busConsumer = ONSFactory.createConsumer(busMqConfig.getConsumerProperties());
        // busConsumer.start();
        System.out.println("消费者初始化完成");
    }
    public void start(){
        busConsumer.start();
    }
    public void onMessage(){
        busConsumer.subscribe(busMqConfig.getTopic(), busMqConfig.getSubExpression(), new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext context) {
                // System.out.println(JSON.toJSONString(message));
                System.out.println("Receive: " + message);
                System.out.println(new String(message.getBody()));
                return Action.CommitMessage;
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        busConsumer.shutdown();
        System.out.println("停止");
    }

}
