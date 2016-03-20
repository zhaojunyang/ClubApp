package com.geminno.club.model;
/*
 * 俱乐部和用户关系表
 * */
public class ClubUser {
	private int cu_id; //主键
	private Club club ;//获得俱乐部id
	private User user;//获得用户id
	private String role;//用户角色
	private ClubUser(){}
	private ClubUser( Club club, User user, String role) {
		super();
		this.club = club;
		this.user = user;
		this.role = role;
	}
	private ClubUser(int cu_id, Club club, User user, String role) {
		super();
		this.cu_id = cu_id;
		this.club = club;
		this.user = user;
		this.role = role;
	}

	public int getCu_id() {
		return cu_id;
	}

	public void setCu_id(int cu_id) {
		this.cu_id = cu_id;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ClubUser [cu_id=" + cu_id + ", club=" + club + ", user=" + user
				+ ", role=" + role + "]";
	}

	
}
