package com.kemalakcicek.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = { "com.kemalakcicek" })
@EnableJpaRepositories(basePackages = { "com.kemalakcicek" })
@ComponentScan(basePackages = { "com.kemalakcicek" })
@EnableJpaAuditing // Localde otomatik olarak tarih verir
@EnableScheduling
public class BankingApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplicationStarter.class, args);
	}

}
