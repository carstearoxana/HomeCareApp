package com.sda.homeCare.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.sda.homeCare")
@EntityScan("com.sda.homeCare.entities")
@EnableJpaRepositories("com.sda.homeCare.repositories")
public class SpringConfig {
}
