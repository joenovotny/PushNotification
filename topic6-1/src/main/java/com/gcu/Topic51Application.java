package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@ComponentScan({ "com.gcu" })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Topic51Application {
	public static void main(String[] args) {
		SpringApplication.run(Topic51Application.class, args);
	}
}