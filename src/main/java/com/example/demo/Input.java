package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Input {
	@Size(min=1, max=20, message="1～20文字で入力してください。")
	private String title;
	@NotBlank(message="コメントを入力してください。")
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
