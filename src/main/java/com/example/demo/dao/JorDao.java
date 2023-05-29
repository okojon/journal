package com.example.demo.dao;

import java.util.ArrayList;
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
			db.update("INSERT INTO journal (title, comment) VALUES(?, ?)",input.getTitle(), input.getComment() );
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
}
