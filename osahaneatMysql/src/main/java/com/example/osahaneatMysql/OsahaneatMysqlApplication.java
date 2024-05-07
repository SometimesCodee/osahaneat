package com.example.osahaneatMysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OsahaneatMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsahaneatMysqlApplication.class, args);
	}

}
