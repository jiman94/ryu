package com.msa.template.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfiguration {

	@Bean
	public Executor executor() {
		Executor executor =
			Executors.newFixedThreadPool(
				50,
				r -> {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				});
		return executor;
	}
}
