package com.javainuse.service;

import com.javainuse.model.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


//@Service
public class RabbitMQReceiver {

	@RabbitListener(queues = "javainuse.queue")
	public void consumer(Message message)
	{
		System.out.println("Consumed Message--"+message.getPayload() );
	}
}