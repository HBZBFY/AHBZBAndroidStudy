package com.feiyue.gulimail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GulimailProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimailProductApplication.class, args);
	}

}
