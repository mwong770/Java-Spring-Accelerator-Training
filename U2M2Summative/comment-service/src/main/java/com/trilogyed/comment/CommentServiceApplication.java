package com.trilogyed.comment;

import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class CommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}
