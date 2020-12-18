package com.example.mThread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoMultiThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMultiThreadApplication.class, args);
	}

}
