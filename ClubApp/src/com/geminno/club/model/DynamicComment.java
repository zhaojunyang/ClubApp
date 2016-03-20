package com.geminno.club.model;
/*
 * 动态与评论人关系表
 * */
public class DynamicComment {
	private int dc_id;//主键
	private Dynamic dynamic;//获取动态id
	private User user;//获取评论人id
	private String  content;//评论内容
	private DynamicComment(){}
	private DynamicComment(Dynamic dynamic, User user, String content) {
		super();
		this.dynamic = dynamic;
		this.user = user;
		this.content = content;
	}
	private DynamicComment(int dc_id, Dynamic dynamic, User user, String content) {
		super();
		this.dc_id = dc_id;
		this.dynamic = dynamic;
		this.user = user;
		this.content = content;
	}

	public int getDc_id() {
		return dc_id;
	}

	public void setDc_id(int dc_id) {
		this.dc_id = dc_id;
	}

	public Dynamic getDynamic() {
		return dynamic;
	}

	public void setDynamic(Dynamic dynamic) {
		this.dynamic = dynamic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "DynamicComment [dc_id=" + dc_id + ", dynamic=" + dynamic
				+ ", user=" + user + ", content=" + content + "]";
	}
	
}
