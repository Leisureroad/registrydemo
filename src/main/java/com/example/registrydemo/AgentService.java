package com.example.registrydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class AgentService {
    @Value("${lastName}")
    String lastName;

    @Value("${foo1}")
    String foo1;

    @Autowired
    AgentClient agentClient;

    public String getName() {
        return agentClient.getName();
    }

    public String getLastName() {
        return lastName;
    }

    public String getFoo1() {
        return foo1;
    }
}
