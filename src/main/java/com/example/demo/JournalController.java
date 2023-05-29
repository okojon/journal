package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.JorDao;
import com.example.demo.entity.JorForm;



@Controller
public class JournalController {

	//SampleDaoの用意
	private final JorDao jordao;
	@Autowired
	public JournalController(JorDao jordao) {
		this.jordao = jordao;
	}

	
	@RequestMapping("/index")
	public String sample(Model model, Input input) {
		model.addAttribute("message", "日報提出");
		List<JorForm> list = jordao.searchDb();
		model.addAttribute("dbList",list);
		return "form/index";
	}

	@RequestMapping("/form")
	public String form(Model model,Input input) {
		model.addAttribute("title","日報：新規投稿");
		return "form/form";
	}
	 
}
