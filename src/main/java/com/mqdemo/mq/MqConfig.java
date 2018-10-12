package com.mqdemo.mq;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@ConfigurationProperties(prefix = "rocketmq")
public class MqConfig {
    @Value("${mq.topic.business}")
    private String topic;
    @Value("${mq.producerId.business}")
    private String producerId;
    @Value("${mq.consumerId.business}")
    private String consumerId;
    @Value("${mq.accesskey}")
    private String accesskey;
    @Value("${mq.secretkey}")
    private String secretkey;
    @Value("${mq.onsaddr}")
    private String onsaddr;
    @Value("${mq.subExpression}")
    private String subExpression;

    public String getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }

    //提供消费者的配置
    public Properties getConsumerProperties() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.ConsumerId, consumerId);
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, accesskey);
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, secretkey);
        consumerProperties.setProperty(PropertyKeyConst.ONSAddr, onsaddr);
        return consumerProperties;
    }
    //提供生产者的配置
    public Properties getProducerProperties() {
        Properties producerProperties = new Properties();
        producerProperties.setProperty(PropertyKeyConst.ProducerId, producerId);
        producerProperties.setProperty(PropertyKeyConst.AccessKey, accesskey);
        producerProperties.setProperty(PropertyKeyConst.SecretKey, secretkey);
        producerProperties.setProperty(PropertyKeyConst.ONSAddr, onsaddr);
        return producerProperties;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
