package com.userdb.controlllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@GetMapping("/test")
	public String test() {
		return "Hello from DashBoard..";
	}

}
