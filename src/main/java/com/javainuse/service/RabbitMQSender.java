package com.javainuse.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.javainuse.model.Employee;

import java.util.List;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private QueueStats queueStats;

	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;


	public void send(Employee company) {
		amqpTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company);
		//System.out.println(queueStats.queueSize());
	}

}