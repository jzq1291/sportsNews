package com.jzq.model;

import java.util.Date;

public class Message {
	private String openId;
	private Date time;
	private String content;
	private String email;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String openId, String content, String email) {
		super();
		this.openId = openId;
		this.content = content;
		this.email = email;
	}

	public Message(String openId, Date time, String content, String email) {
		super();
		this.openId = openId;
		this.time = time;
		this.content = content;
		this.email = email;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Message [openId=" + openId + ", time=" + time + ", content="
				+ content + ", email=" + email + "]";
	}
	
}
