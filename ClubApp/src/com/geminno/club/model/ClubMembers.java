package com.geminno.club.model;

public class ClubMembers {
	
	private String uid;
	private String username;
	private String level;
	private String joindateline;
	private String lastupdate;
	private int online;
	private int is_friend;
	private String avatar;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getJoindateline() {
		return joindateline;
	}
	public void setJoindateline(String joindateline) {
		this.joindateline = joindateline;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(int is_friend) {
		this.is_friend = is_friend;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public ClubMembers(String uid, String username, String level, String joindateline, String lastupdate, int online,
			int is_friend, String avatar) {
		super();
		this.uid = uid;
		this.username = username;
		this.level = level;
		this.joindateline = joindateline;
		this.lastupdate = lastupdate;
		this.online = online; 
		this.is_friend = is_friend;
		this.avatar = avatar;
	}
	public ClubMembers() {
		super();
	}
	@Override
	public String toString() {
		return "ClubMembers [uid=" + uid + ", username=" + username + ", level=" + level + ", joindateline="
				+ joindateline + ", lastupdate=" + lastupdate + ", online=" + online + ", is_friend=" + is_friend
				+ ", avatar=" + avatar + "]";
	}
	
	
	
	
}
