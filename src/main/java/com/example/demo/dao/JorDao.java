package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Input;
import com.example.demo.entity.JorForm;

@Repository
public class JorDao {
	//JdbcTemplate(DB操作をする職人)の用意
	private final JdbcTemplate db;

	@Autowired
	public JorDao(JdbcTemplate db) {
		this.db = db;
	}

	//登録処理
	public void insertDb(Input input) {
		Date d = new Date();
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
		String c2 = d2.format(d);
		db.update("INSERT INTO journal (date,name,title, comment) VALUES(?,?,?,?)", c2, "佐藤次郎", input.getTitle(),
				input.getComment());
	}

	//データベースの表示
	public List<JorForm> searchDb() {
		String sql = "SELECT * FROM journal";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<JorForm> resultDb2 = new ArrayList<JorForm>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {

			//データ1件分を1つのまとまりとしたEntForm型の「entformdb」を生成
			JorForm entformdb = new JorForm();

			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setDate((String) result1.get("date"));
			entformdb.setName((String) result1.get("name"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setComment((String) result1.get("comment"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;
	}

	public List<JorForm> showCommentDb(Long id) {
		//コンソールに表示
		System.out.println("編集画面を出します");
		System.out.println(id);
		//データベースから目的の1件を取り出して、そのままresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM journal where id=?", id);
		//画面に表示しやすい形のList(resultDB2)を用意
		List<JorForm> resultDb2 = new ArrayList<JorForm>();

		//1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			//データ1件分を1つのまとまりとするので、EntForm型の「entformdb」を生成
			JorForm entformdb = new JorForm();
			//id、nameのデータをentformdbに移す
			entformdb.setId((int) result1.get("id"));
			entformdb.setDate((String) result1.get("date"));
			entformdb.setName((String) result1.get("name"));
			entformdb.setTitle((String) result1.get("title"));
			entformdb.setComment((String) result1.get("comment"));
			//移し替えたデータを持ったentformdbを、resultDB2に入れる
			resultDb2.add(entformdb);
		}

		//Controllerに渡す
		return resultDb2;
	}
}
