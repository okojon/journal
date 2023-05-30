package com.example.demo;

import jakarta.validation.constraints.Size;

public class Input {
	@Size(min=1, max=20, message="タイトルは1～20文字で入力してください。")
	private String title;
	@Size(min=1, max=100, message="コメントは1～100文字で入力してください。")
	private String comment;
	
	public Input() {}
	
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
