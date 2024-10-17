package com.webank.dca.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.webank.dca.store.db")
public class DcaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcaStoreApplication.class, args);
	}

}
