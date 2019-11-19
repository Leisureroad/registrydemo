package com.example.registrydemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableCircuitBreaker
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

	@RequestMapping("/testProps")
	public String testProps() {
		return "name: " + agentService.getLastName() + " foo1: " + agentService.getFoo1() + ", age: " + agentService.getAge();
	}

	@RequestMapping("/testHystrix")
	@HystrixCommand(fallbackMethod = "getBackup")
	public String getGuide() {
		URI uri = UriComponentsBuilder.fromUriString("//publisherdemo/name").build().toUri();
		return restTemplate.getForObject(uri, String.class);
	}

	String getBackup() {
		return "None publisher available! ";
	}

	@RequestMapping("/demo")
	public String update() {
		return "Hello concourse!!!-V2.0";
	}

	@Autowired
	FooConfigurationProperties properties;

	@RequestMapping("/testConfigProps")
	public String testConfigProps() {
		return properties.getName() + " " + properties.getAge();
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrydemoApplication.class, args);
	}
}


