package com.example.registrydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
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
//		return "name: " + agentService.getName();
	}

	public static void main(String[] args) {
		SpringApplication.run(RegistrydemoApplication.class, args);
	}
}


//@Configuration
//class  SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange().anyExchange().permitAll();
//        return http.build();
//    }
//}


