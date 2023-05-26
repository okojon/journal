package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JournalController {

	@RequestMapping("/form")
	public String sample(Model model, Input input) {
		model.addAttribute("message", "日報提出");
		return "form/index";
	}

	 
}
