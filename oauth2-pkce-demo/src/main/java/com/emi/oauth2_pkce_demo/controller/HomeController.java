package com.emi.oauth2_pkce_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/home"))
@CrossOrigin(origins="*")
public class HomeController {

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String home() {
		return "hello";
	}
}
