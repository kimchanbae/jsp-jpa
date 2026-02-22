package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.MemberRepository;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) { 
		model.addAttribute("msg", "springboot+jsp+jpa 프로젝트 시작");
		
		return "home";
	}
}
