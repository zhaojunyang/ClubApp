package com.geminno.club.model;

import java.util.Date;

/*
 * 通讯录表
 * */
public class Communication {
	private int communication_id;//主键
	private User user;//获得通信所属用户id
	private Date communication_date;//获得通讯录创建时间
	private Communication(){}
	private Communication( User user,
			Date communication_date) {
		super();
		this.user = user;
		this.communication_date = communication_date;
	}
	private Communication(int communication_id, User user,
			Date communication_date) {
		super();
		this.communication_id = communication_id;
		this.user = user;
		this.communication_date = communication_date;
	}

	public int getCommunication_id() {
		return communication_id;
	}

	public void setCommunication_id(int communication_id) {
		this.communication_id = communication_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCommunication_date() {
		return communication_date;
	}

	public void setCommunication_date(Date communication_date) {
		this.communication_date = communication_date;
	}

	@Override
	public String toString() {
		return "Communication [communication_id=" + communication_id
				+ ", user=" + user + ", communication_date="
				+ communication_date + "]";
	}
	
	
}
