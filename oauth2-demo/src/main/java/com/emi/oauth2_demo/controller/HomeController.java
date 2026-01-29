package com.emi.oauth2_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
    @GetMapping("/")
    public String root() {
        return "home";
    }
}
