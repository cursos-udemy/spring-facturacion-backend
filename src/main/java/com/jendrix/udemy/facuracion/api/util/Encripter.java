package com.jendrix.udemy.facuracion.api.util;

import java.util.stream.IntStream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encripter {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "willy";		
		IntStream.rangeClosed(1, 3).forEach(index -> System.out.println(encoder.encode(password)));
	}

}
