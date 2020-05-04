package com.github.dobrosi.cloudscanner.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.github.dobrosi.cloudscanner.repository")
@EnableJpaRepositories("com.github.dobrosi.cloudscanner.repository")
public class CloudScannerApplicationConfiguration {
}