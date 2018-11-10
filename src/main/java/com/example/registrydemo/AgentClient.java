package com.example.registrydemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("publisherdemo")
interface AgentClient {
    @RequestMapping(value = "/name", method = GET)
    String getName();
}
