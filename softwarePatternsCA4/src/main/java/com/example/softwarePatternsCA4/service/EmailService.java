package com.example.softwarePatternsCA4.service;


import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	public void sendConfirmation(String to, String subject, String body) {
		System.out.println("Email to " + to + ": " + subject);
	}

}
