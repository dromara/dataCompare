package com.vince.xq.dataCompare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vince.xq.dataCompare.dao")
@SpringBootApplication
public class DataCompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataCompareApplication.class, args);
	}

}
