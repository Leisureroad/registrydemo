package com.example.registrydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootApplication
@RestController
@EnableFeignClients
public class RegistrydemoApplication {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/")
	public String foo() {
		URI uri = UriComponentsBuilder.fromUriString("//publisherdemo/").build().toUri();
		String result = restTemplate.getForObject(uri,String.class);
		return result;
	}

	@Autowired
	AgentService agentService;

	@RequestMapping("/name")
	public String getName() {
		return agentService.getName() + " name: " + agentService.getLastName() + " foo1: " + agentService.getFoo1();
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrydemoApplication.class, args);
	}
}


