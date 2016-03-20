package com.geminno.club.entity;

public class MessageInfo {

	private String id ;
	private String authorid ;
	private String author ;
	private String dateline ;
	private String message ;
	private String numbers ;
	private String status ;
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	private String avatar ;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public MessageInfo(String id, String authorid, String author,
			String dateline, String message, String numbers, String status,
			String avatar) {
		super();
		this.id = id;
		this.authorid = authorid;
		this.author = author;
		this.dateline = dateline;
		this.message = message;
		this.numbers = numbers;
		this.status = status;
		this.avatar = avatar;
	}
	public MessageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MessageInfo [id=" + id + ", authorid=" + authorid + ", author="
				+ author + ", dateline=" + dateline + ", message=" + message
				+ ", numbers=" + numbers + ", status=" + status + ", avatar="
				+ avatar + "]";
	}
	
}
