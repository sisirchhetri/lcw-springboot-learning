package com.elearn.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {


	@GetMapping("/client-login")
	public String loginControl() {
		
		return "login";
	}
	
	@PostMapping("/success")
	public String onSuccess() {
		return "success";
	}
}
