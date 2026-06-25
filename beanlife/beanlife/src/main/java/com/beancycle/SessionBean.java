package com.beancycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@SessionScope
public class SessionBean {
	
	public SessionBean() {
		System.out.println("Constructor of SessionBean BEAN  called !");
	}
	@PostConstruct
	public void postconstruct() {
		System.out.println("Post construction of SessionBean BEAN !");
	}

	@PreDestroy
	public void preDistroy() {
		System.out.println("Before SessionBean BEAN is getting distroy !");
	}
}
