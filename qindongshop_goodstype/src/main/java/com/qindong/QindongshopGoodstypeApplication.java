package com.qindong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qindong.dao")
public class QindongshopGoodstypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QindongshopGoodstypeApplication.class, args);
	}

}
