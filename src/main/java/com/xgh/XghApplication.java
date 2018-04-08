package com.xgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass=true)
@SpringBootApplication
public class XghApplication {
	public static void main(String[] args) {
		SpringApplication.run(XghApplication.class, args);
	}
}
