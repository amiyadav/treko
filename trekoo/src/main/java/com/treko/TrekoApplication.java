package com.treko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.treko.audit.SpringSecurityAuditorAware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication(scanBasePackages="com.treko")
@EnableElasticsearchRepositories(basePackages = "com.treko.es.repo")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class TrekoApplication {
	private static final Logger LOGGER = LogManager.getLogger(TrekoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TrekoApplication.class, args);

		LOGGER.info("Info level log message");
		LOGGER.debug("Debug level log message");
		LOGGER.error("Error level log message");
	}
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SpringSecurityAuditorAware();
	}
}
