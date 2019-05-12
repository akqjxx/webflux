package cn.com.et.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.et.service.Service1;

@RestController
public class Controller1 {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Service1 service;

	@RequestMapping(value = "/block", method = RequestMethod.GET)
	public Callable<User> executeSlowTask() {
		logger.info("Request received");
		// String result = service1.execute();
		Callable<User> callable = () -> {
			return service.execute("111");
		};
		logger.info("Servlet thread released");

		return callable;
	}

	@RequestMapping(value = "/block1", method = RequestMethod.GET)
	public Callable<String> executeSlowTask1() {
		logger.info("Request received");
		// String result = service1.execute();
		Callable<String> callable =  
			  service::execute;
		 
		logger.info("Servlet thread released");

		return callable;
	}
}
