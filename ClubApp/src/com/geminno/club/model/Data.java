package com.geminno.club.model;

public class Data {

	private String username ;//用户名
	private String usergroup ;//提示语“新手上路”
	private int uid ;//用户id
	private String sauthtoken ;//登录后获取的用户令牌
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSauthtoken() {
		return sauthtoken;
	}
	public void setSauthtoken(String sauthtoken) {
		this.sauthtoken = sauthtoken;
	}
	public Data(String username, String usergroup, int uid, String sauthtoken) {
		super();
		this.username = username;
		this.usergroup = usergroup;
		this.uid = uid;
		this.sauthtoken = sauthtoken;
	}
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Data [username=" + username + ", usergroup=" + usergroup
				+ ", uid=" + uid + ", sauthtoken=" + sauthtoken + "]";
	}
	
}
