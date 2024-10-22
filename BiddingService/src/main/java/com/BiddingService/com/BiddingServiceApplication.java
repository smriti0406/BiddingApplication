package com.BiddingService.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BiddingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiddingServiceApplication.class, args);
	}

}
