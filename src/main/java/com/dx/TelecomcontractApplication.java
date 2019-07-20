package com.dx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.dx.mapper")
public class TelecomcontractApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelecomcontractApplication.class, args);
	}

}
