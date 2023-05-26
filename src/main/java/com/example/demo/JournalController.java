package com.example.demo;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class JournalController {

	@RequestMapping("/form")
	public String sample(Model model) {
		model.addAttribute("message", "日報提出");
		return "index";
	}

	 
}
