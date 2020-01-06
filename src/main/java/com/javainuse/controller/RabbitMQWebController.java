package com.javainuse.controller;

import com.javainuse.service.QueueStats;
import com.javainuse.service.RabbitMQReceiver;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;
import com.javainuse.service.RabbitMQSender;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/javainuse-rabbitmq/")
@Api(description = "Rabbit Controller")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	//@Autowired
	RabbitMQReceiver rabbitMQReceiver;

	@Autowired
	QueueStats queueStats;

	@GetMapping(value = "/producer")
	@ApiOperation(value = "Producer based on Name and ID")
	public String producer(@ApiParam(value = "Employee Name") @RequestParam("empName") String empName/*, @ApiParam(value = "Employee ID") @RequestParam("empId") String empId*/) {
		Employee emp=new Employee();
//		emp.setEmpId(empId);
		emp.setEmpName(empName);
		rabbitMQSender.send(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

	@GetMapping(value = "/queueSize")
	@ApiOperation(value = "Current Queue Size")
	public String getQueueSize()
	{
		String s = queueStats.queueSize();
		return s;
	}
//	@GetMapping(value = "/consumer")
//	public void consumer()
//	{
//		rabbitMQReceiver.receive();
//	}
}

