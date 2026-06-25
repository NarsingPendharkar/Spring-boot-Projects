package com.beancycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Scope("singleton")
public class SingletonBean {
	
	public SingletonBean() {
		System.out.println("Constructor of singleton bean called !");
	}
	@PostConstruct
	public void postconstruct() {
		System.out.println("Post construction of bean !");
	}

	@PreDestroy
	public void preDistroy() {
		System.out.println("Before bean is getting distroy !");
	}
}
