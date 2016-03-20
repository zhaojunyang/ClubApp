package com.geminno.club.model;
/*
 * 点赞表
 * 
 * */
public class Praise {
	private int praise_id;//主键
	private Dynamic dynamic;//获取动态id
	private User user;//获取点赞用户id
	
	private Praise(Dynamic dynamic, User user) {
		super();
		this.dynamic = dynamic;
		this.user = user;
	}
	private Praise(int praise_id, Dynamic dynamic, User user) {
		super();
		this.praise_id = praise_id;
		this.dynamic = dynamic;
		this.user = user;
	}

	public int getPraise_id() {
		return praise_id;
	}

	public void setPraise_id(int praise_id) {
		this.praise_id = praise_id;
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

	@Override
	public String toString() {
		return "Praise [praise_id=" + praise_id + ", dynamic=" + dynamic
				+ ", user=" + user + "]";
	}
	
}
