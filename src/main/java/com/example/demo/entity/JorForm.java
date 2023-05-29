package com.example.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class JorForm {
	private int id;
	private String date;
	private String name;
	private String title;
	private String comment;
	
	public JorForm() {
		this.name = "佐藤次郎";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
