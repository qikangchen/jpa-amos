package com.github.qikangchen.spring.data.normalized.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	/**
	 * Start internal H2 server so we can query the DB from IDE
	 *
	 */
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server h2Server() throws SQLException {
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//	}
}
