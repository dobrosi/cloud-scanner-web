package com.github.dobrosi.cloudscanner.application;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.SpringApplication.run;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.dobrosi.cloudscanner.configuration.CloudScannerApplicationConfiguration;
import com.github.dobrosi.cloudscanner.repository.BarcodeUser;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@SpringBootApplication
@Import(CloudScannerApplicationConfiguration.class)
public class CloudScannerApplication {
	Logger logger = getLogger(CloudScannerApplication.class);

	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	public static void main(String[] args) {
		run(CloudScannerApplication.class, args);
	}

	@PostConstruct
	public void createTestAdmin() {
		BarcodeUser admin = new BarcodeUser();
		admin.setEmail("test@test.com");
		admin.setFirstName("Elek");
		admin.setLastName("Teszt");
		String adminLoginId = barcodeUserRepository.save(admin).getLoginId();
		logger.info("Admin loginId: " + adminLoginId);
	}

}