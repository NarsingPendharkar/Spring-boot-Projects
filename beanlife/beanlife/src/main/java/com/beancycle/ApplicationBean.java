package com.beancycle;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@ApplicationScope
public class ApplicationBean {

    public ApplicationBean() {
        System.out.println("Constructor of ApplicationBean called!");
    }

    @PostConstruct
    public void init() {
        System.out.println("ApplicationBean initialized (@PostConstruct)");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ApplicationBean is being destroyed (@PreDestroy)");
    }
}