package com.sunwell.product.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ProdConfig {
	
	@Bean
	public PBLogger pbLogger() {
		return new PBLogger();
	}

}
