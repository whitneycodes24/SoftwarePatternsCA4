package com.example.softwarePatternsCA4.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {

	@GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard() {
		
		String summary = "Admin's Dashboard: \n" +
		                 "Total Books - 23 \n" +
				         "Total Customer - 11 \n";
		return ResponseEntity.ok(summary);
	}
	
	@PostMapping("/simulateUpdate")
    public ResponseEntity<String> simulateUpdateAction(@RequestParam String action) {
        //placeholder come back to this 
        return ResponseEntity.ok("Action '" + action + "' received.");
    }
}
