package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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

	// 「日報提出」画面の表示
	@RequestMapping("/index")
	public String sample(Model model, Input input) {
		model.addAttribute("title", "日報提出");
		Input entform = new Input();
		entform.setTitle(input.getTitle());
		entform.setComment(input.getComment());
		if ((input.getTitle() != "") && (input.getTitle() != null) && (input.getComment() != "")
				&& (input.getComment() != null)) {
			jordao.insertDb(entform);
		}

		List<JorForm> list = jordao.searchDb();
		model.addAttribute("dbList", list);
		return "form/index";
	}

	// 「日報：新規投稿」画面の表示
	@RequestMapping("/form")
	public String form(Model model, Input input) {
		model.addAttribute("title", "日報：新規投稿");
		return "form/form";
	}

	// 「日報：新規投稿（確認画面）」の表示
	@RequestMapping("/confirm")
	public String confirm(@Validated Input input, BindingResult result, Model model) {

		// バリデーションによるエラー文の表示
		if (result.hasErrors()) {
			model.addAttribute("title", "日報：新規投稿");
			return "form/form";
		}

		model.addAttribute("title", "日報：新規投稿（確認画面）");
		return "form/confirm";
	}

	// 「日報（内容を表示）」の表示
	@RequestMapping("/content/{id}")
	public String contentView(@PathVariable Long id, Model model) {

		//DBからデータを1件取ってくる(リストの形)
		List<JorForm> list = jordao.showCommentDb(id);

		//リストから、オブジェクトだけをピックアップ
		JorForm entformdb = list.get(0);

		//スタンバイしているViewに向かって、データを投げる
		model.addAttribute("dbList", entformdb);
		model.addAttribute("title", "日報（内容を表示）");
		return "form/content";
	}
	
	//更新画面の表示(SELECT)
			@RequestMapping("/edit/{id}")
			public String editView(@PathVariable Long id, Model model) {

				//DBからデータを1件取ってくる(リストの形)
				List<JorForm> list = jordao.selectOne(id);

				//リストから、オブジェクトだけをピックアップ
				JorForm entformdb = list.get(0);

				//スタンバイしているViewに向かって、データを投げる
				model.addAttribute("form", entformdb);
				model.addAttribute("title", "編集ページ");
				return "form/edit";
			}
			//更新処理(UPDATE)
			@RequestMapping("/edit/{id}/exe")
			public String editExe(@PathVariable Long id, Model model, Input input) {
				//フォームの値をエンティティに入れ直し
				JorForm entform = new JorForm();
				entform.setTitle(input.getTitle());
				entform.setComment(input.getComment());
				//更新の実行
				jordao.updateDb(id,entform);
				//一覧画面へリダイレクト
				return "redirect:/index";
			}
			
			//削除(DELETE)
			@RequestMapping("/del/{id}")
			public String destory(@PathVariable Long id) {
				jordao.deleteDb(id);
				return "redirect:/index";
			}
}
