package com.reactive.project.reactive.programing;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class MyController {
	
	
	@GetMapping(value="/greet")
	public Mono<String> greet()
	{
		
		return computeMessage().zipWith(getMessageFromDB())
				.map(value->{
					return value.getT1()+ value.getT2()	;
					});
	}

	
	private Mono<String> computeMessage()
	{
		
		return Mono.just("Hello").delayElement(Duration.ofSeconds(5));
	}
	
	private Mono<String> getMessageFromDB()
	{
		
		return Mono.just("   Manoj").delayElement(Duration.ofSeconds(5));
	}
}
