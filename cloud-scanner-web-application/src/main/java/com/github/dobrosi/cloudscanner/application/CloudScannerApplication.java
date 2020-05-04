package com.github.dobrosi.cloudscanner.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.dobrosi.cloudscanner.configuration.CloudScannerApplicationConfiguration;
import com.github.dobrosi.cloudscanner.configuration.SecurityConfig;

@SpringBootApplication
@Import({CloudScannerApplicationConfiguration.class, SecurityConfig.class})
public class CloudScannerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudScannerApplication.class, args);
	}

}