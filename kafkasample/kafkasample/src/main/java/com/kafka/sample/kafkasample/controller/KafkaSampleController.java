package com.kafka.sample.kafkasample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.sample.kafkasample.model.User;

@RestController
@RequestMapping("/v1")
public class KafkaSampleController {
	
	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	private static final String TOPIC = "vishal_topic";
	
	
	@GetMapping("publish/{userName}")
	public ResponseEntity<User> publishMessage(@PathVariable("userName") String userName
	)
	{
		HttpStatus status = HttpStatus.OK;
		
		User user = new User(userName,"vishal", "choudarapu");
		
		kafkaTemplate.send(TOPIC,user);
		
		return new ResponseEntity<User>(user,status);
	}

}
