package com.beancycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeanlifeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BeanlifeApplication.class, args);
		
		  	System.out.println("\n===== Singleton Scope =====");

	        SingletonBean s1 = context.getBean(SingletonBean.class);
	        SingletonBean s2 = context.getBean(SingletonBean.class);

	        System.out.println("Same Object ? " + (s1 == s2));

	        System.out.println("\n===== Prototype Scope =====");

	        PrototypeBean p1 = context.getBean(PrototypeBean.class);
	        PrototypeBean p2 = context.getBean(PrototypeBean.class);

	        System.out.println("Same Object ? " + (p1 == p2));

	        context.close();
	}

}
