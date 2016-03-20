package com.geminno.club.model;
/*
 * �û�ͨѶ¼��ϵ��
 * */
public class CommunicationUser {
	private int com_user_id;//����
	private Communication communication;//���ͨѶ¼id
	private User user;//���ͨѶ¼���û�id
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
