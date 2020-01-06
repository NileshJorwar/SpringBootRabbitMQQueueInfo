package com.javainuse.service;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class QueueStats {

    @Autowired
    RabbitAdmin rabbitAdmin;

    public String queueSize()
    {
        Properties properties;
        properties=rabbitAdmin.getQueueProperties("javainuse.queue");
        //System.out.println("QUeue Size--"+properties.get("QUEUE_MESSAGE_COUNT").toString());
        return "Current Queue Size--"+ properties.get("QUEUE_MESSAGE_COUNT").toString();
    }

}
