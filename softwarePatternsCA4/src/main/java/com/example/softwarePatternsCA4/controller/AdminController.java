package com.example.softwarePatternsCA4.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@GetMapping("/noticeboard")
	public String adminOnly() {
		return "Welcome Admin!";
	}

}
