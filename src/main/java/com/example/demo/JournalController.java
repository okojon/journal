package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
		Input entform = new Input();
		entform.setTitle(input.getTitle());
		entform.setComment(input.getComment());
		if ((input.getTitle() != "") && (input.getTitle() != null) && (input.getComment() != "") && (input.getComment() != null)) {
			jordao.insertDb(entform);
		}
		
		List<JorForm> list = jordao.searchDb();
		model.addAttribute("dbList",list);
		return "form/index";
	}

	@RequestMapping("/form")
	public String form(Model model,Input input) {
		model.addAttribute("title","日報：新規投稿");
		return "form/form";
	}
	 
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {

		if(result.hasErrors()) {
		model.addAttribute("title","日報：新規投稿");
		return "form/form";
		}

		model.addAttribute("title","日報：新規投稿（確認画面）");
		return "form/confirm";
	}

	
}
