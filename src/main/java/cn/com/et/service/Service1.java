package cn.com.et.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.et.controller.User;

@Service
public class Service1 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	 public User execute(String str) {
	        try {
	            Thread.sleep(5000);
	            logger.info("Slow task executed");
	            User user = new User();
	            user.setId(123);
	            user.setName("zhangsan");
	            return user;
	        } catch (InterruptedException e) {
	            throw new RuntimeException();
	        }
	    }
	 
	 public String execute() {
	        try {
	            Thread.sleep(5000);
	            logger.info("Slow task executed");
	            User user = new User();
	            user.setId(123);
	            user.setName("zhangsan");
	            return "hello";
	        } catch (InterruptedException e) {
	            throw new RuntimeException();
	        }
	    }
}
