package com.userdb.controlllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/search")
public class SearchController {

	@GetMapping("/test")
	public String test() {
		return "Hello From Search";
	}
	
}
