package com.geminno.club.model;
/*
 * 论坛表
 * */
public class Forum {
	private int forum_id;//主键
	private Club club;//俱乐部对象    获得俱乐部id
	private int forum_name;//论坛名称
	private String fl_URL;//论坛logoURL
	private int amount;//论坛人数
	
	private Forum(){}
	private Forum(Club club, int forum_name, String fl_URL,
			int amount) {
		super();
		this.club = club;
		this.forum_name = forum_name;
		this.fl_URL = fl_URL;
		this.amount = amount;
	}
	private Forum(int forum_id, Club club, int forum_name, String fl_URL,
			int amount) {
		super();
		this.forum_id = forum_id;
		this.club = club;
		this.forum_name = forum_name;
		this.fl_URL = fl_URL;
		this.amount = amount;
	}
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public int getForum_name() {
		return forum_name;
	}
	public void setForum_name(int forum_name) {
		this.forum_name = forum_name;
	}
	public String getFl_URL() {
		return fl_URL;
	}
	public void setFl_URL(String fl_URL) {
		this.fl_URL = fl_URL;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Forum [forum_id=" + forum_id + ", club=" + club
				+ ", forum_name=" + forum_name + ", fl_URL=" + fl_URL
				+ ", amount=" + amount + "]";
	}
	
}
