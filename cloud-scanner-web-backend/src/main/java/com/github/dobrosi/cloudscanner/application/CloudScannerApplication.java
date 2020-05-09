package com.github.dobrosi.cloudscanner.application;

import static com.github.dobrosi.cloudscanner.repository.BarcodeUserFactory.create;
import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.SpringApplication.run;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.github.dobrosi.cloudscanner.configuration.CloudScannerApplicationConfiguration;
import com.github.dobrosi.cloudscanner.configuration.SpringFoxConfig;
import com.github.dobrosi.cloudscanner.repository.BarcodeUserRepository;

@SpringBootApplication
@Import({CloudScannerApplicationConfiguration.class, SpringFoxConfig.class})
public class CloudScannerApplication {
	Logger logger = getLogger(CloudScannerApplication.class);

	@Autowired
	private BarcodeUserRepository barcodeUserRepository;

	public static void main(String[] args) {
		run(CloudScannerApplication.class, args);
	}

	@PostConstruct
	private void createTestAdmin() {
		logger
				.info(
						format(
								"Admin loginId: %s",
								barcodeUserRepository
										.save(create().withEmail("test@test.com").withFirstName("firstName").withLastName("lastName").asAdmin().build())));
	}
}
