package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JorDao {
	//JdbcTemplate(DB操作をする職人)の用意
		private final JdbcTemplate db;
		@Autowired
		public JorDao(JdbcTemplate db) {
			this.db = db;
		}
		
}
