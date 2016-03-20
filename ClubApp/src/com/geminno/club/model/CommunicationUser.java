package com.geminno.club.model;
/*
 * 用户通讯录关系表
 * */
public class CommunicationUser {
	private int com_user_id;//主键
	private Communication communication;//获得通讯录id
	private User user;//获得通讯录中用户id
	private CommunicationUser(){}
	private CommunicationUser( Communication communication,
			User user) {
		super();
		this.communication = communication;
		this.user = user;
	}
	private CommunicationUser(int com_user_id, Communication communication,
			User user) {
		super();
		this.com_user_id = com_user_id;
		this.communication = communication;
		this.user = user;
	}
	public int getCom_user_id() {
		return com_user_id;
	}
	public void setCom_user_id(int com_user_id) {
		this.com_user_id = com_user_id;
	}
	public Communication getCommunication() {
		return communication;
	}
	public void setCommunication(Communication communication) {
		this.communication = communication;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "CommunicationUser [com_user_id=" + com_user_id
				+ ", communication=" + communication + ", user=" + user + "]";
	}
	
}
