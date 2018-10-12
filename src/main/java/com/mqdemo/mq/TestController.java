package com.mqdemo.mq;

import com.aliyun.openservices.ons.api.Message;
import com.youhujia.yhcloud.brane.common.BaseController;
import com.youhujia.yhcloud.brane.common.COMMON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy on 2018/10/12.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController {


    @Autowired
    private MqProducer producers;
    @Autowired
    private MqConfig mqConfig;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public COMMON.SimpleResponse getOrderDetailByOrderId() {
        try {
            Message message = new Message(mqConfig.getTopic(), "TagA", "测试发送消息".getBytes());
            producers.sentMessage(message);
            return null;
        } catch (Exception e) {
            return handleException(result -> COMMON.SimpleResponse.newBuilder().setResult(result).build(), e);
        }
    }
}
