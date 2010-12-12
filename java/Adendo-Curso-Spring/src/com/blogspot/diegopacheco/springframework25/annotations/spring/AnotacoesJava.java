package com.blogspot.diegopacheco.springframework25.annotations.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class AnotacoesJava {
	
	@PostConstruct
	public void init(){
		System.out.println("C�digo de inicializa��o...");
	}
	
	@PreDestroy
	public void shutdown(){
		System.out.println("C�digo de shutdown...");
	}
	
}
