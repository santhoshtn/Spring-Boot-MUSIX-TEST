package com.stackroute.muzixApp;

import com.stackroute.muzixApp.domain.Track;
import com.stackroute.muzixApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@PropertySource("classpath:application.properties")
	public class MuzixAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(MuzixAppApplication.class, args);
	}

}

