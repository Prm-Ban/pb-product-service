package com.sunwell.product.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestBean {
	
	public void testMethod() {
		System.out.println("testMethod is called");
//		return null;
	}

}
