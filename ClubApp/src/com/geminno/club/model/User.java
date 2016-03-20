package com.geminno.club.model;
/*
 * 用户表实体
 * */
public class User {
	private int user_id;//用户id
	private int purse;//用户钱包
	private String nick_name;//昵称
	private String password;//密码
	private String user_name;//账号
	private String photo_URL;//头像URL
	private String td_code_URL;//二维码URL
	private String sex ;//性别
	private String address;//地区
	private String signature;//个人签名
	public User(){}
	public User( int purse, String nick_name, String password,
			String user_name, String photo_URL, String td_code_URL, String sex,
			String address, String signature) {
		super();
		this.purse = purse;
		this.nick_name = nick_name;
		this.password = password;
		this.user_name = user_name;
		this.photo_URL = photo_URL;
		this.td_code_URL = td_code_URL;
		this.sex = sex;
		this.address = address;
		this.signature = signature;
	}
	public User(int user_id, int purse, String nick_name, String password,
			String user_name, String photo_URL, String td_code_URL, String sex,
			String address, String signature) {
		super();
		this.user_id = user_id;
		this.purse = purse;
		this.nick_name = nick_name;
		this.password = password;
		this.user_name = user_name;
		this.photo_URL = photo_URL;
		this.td_code_URL = td_code_URL;
		this.sex = sex;
		this.address = address;
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", purse=" + purse + ", nick_name="
				+ nick_name + ", password=" + password + ", user_name="
				+ user_name + ", photo_URL=" + photo_URL + ", td_code_URL="
				+ td_code_URL + ", sex=" + sex + ", address=" + address
				+ ", signature=" + signature + "]";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPurse() {
		return purse;
	}
	public void setPurse(int purse) {
		this.purse = purse;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPhoto_URL() {
		return photo_URL;
	}
	public void setPhoto_URL(String photo_URL) {
		this.photo_URL = photo_URL;
	}
	public String getTd_code_URL() {
		return td_code_URL;
	}
	public void setTd_code_URL(String td_code_URL) {
		this.td_code_URL = td_code_URL;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}
