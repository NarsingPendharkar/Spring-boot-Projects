package com.beancycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Scope("prototype")
public class PrototypeBean {
	
	public PrototypeBean() {
		System.out.println("Constructor of PROTOTYPE BEAN  called !");
	}
	@PostConstruct
	public void postconstruct() {
		System.out.println("Post construction of PROTOTYPE BEAN !");
	}

	@PreDestroy
	public void preDistroy() {
		System.out.println("Before PROTOTYPE BEAN is getting distroy !");
	}
}
