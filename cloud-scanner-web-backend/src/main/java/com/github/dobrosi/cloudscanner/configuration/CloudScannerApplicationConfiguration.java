package com.github.dobrosi.cloudscanner.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@Configuration
@ComponentScan(basePackages = "com.github.dobrosi.cloudscanner")
@EntityScan("com.github.dobrosi.cloudscanner.repository")
@EnableJpaRepositories("com.github.dobrosi.cloudscanner.repository")
@EnableJpaAuditing
@Import({ SpringDataRestConfiguration.class, SpringFoxConfig.class })
public class CloudScannerApplicationConfiguration {
}