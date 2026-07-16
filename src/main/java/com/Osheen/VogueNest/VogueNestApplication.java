package com.Osheen.VogueNest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * VogueNestApplication - Main Entry Point
 * We explicitly exclude SecurityAutoConfiguration here to completely turn off
 * Spring Security's automatic login blocks, keeping your application fast and error-free!
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class VogueNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VogueNestApplication.class, args);
	}
}