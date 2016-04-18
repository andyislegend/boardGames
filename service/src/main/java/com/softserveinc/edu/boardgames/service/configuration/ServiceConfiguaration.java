package com.softserveinc.edu.boardgames.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.softserveinc.edu.boardgames.persistence.configuration.PersistanceConfiguration;


@Configuration
@Import(PersistanceConfiguration.class)
@ComponentScan("com.softserveinc.edu")
public class ServiceConfiguaration {
	
}