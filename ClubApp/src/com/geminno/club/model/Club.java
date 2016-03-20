package com.geminno.club.model;

import java.io.Serializable;

public class Club implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//我的俱乐部（同城俱乐部）需要的字段
	private int id; //俱乐部ID
	private String club_name;//俱乐部名字
	private String club_LOGO;//俱乐部的LOGO
	private String club_brief;//俱乐部的简介
	private int addNumber;//俱乐部的人数
	
	//俱乐部主页需要的字段
	private String club_location;//俱乐部的位置
	private String club_type;//俱乐部的类型
	private int invitation_posts;//帖子总数
	private String club_Dateline;//俱乐部的创建时间
	private String founderUid;//创建者id
	private String founderName;//创建者名字
	private String founderUrl;//创建者LOGO
	private int is_join;
	
	
	
	public Club(int id, String club_name, String club_LOGO, String club_brief, int addNumber, String club_location,
			String club_type, int invitation_posts, String club_Dateline, String founderUid, String founderName,
			String founderUrl, int is_join) {
		super();
		this.id = id;
		this.club_name = club_name;
		this.club_LOGO = club_LOGO;
		this.club_brief = club_brief;
		this.addNumber = addNumber;
		this.club_location = club_location;
		this.club_type = club_type;
		this.invitation_posts = invitation_posts;
		this.club_Dateline = club_Dateline;
		this.founderUid = founderUid;
		this.founderName = founderName;
		this.founderUrl = founderUrl;
		this.is_join = is_join;
	}

	public int getIs_join() {
		return is_join;
	}

	public void setIs_join(int is_join) {
		this.is_join = is_join;
	}

	public String getClub_location() {
		return club_location;
	}
	public void setClub_location(String club_location) {
		this.club_location = club_location;
	}
	public String getClub_type() {
		return club_type;
	}
	public void setClub_type(String club_type) {
		this.club_type = club_type;
	}
	public int getInvitation_posts() {
		return invitation_posts;
	}
	public void setInvitation_posts(int invitation_posts) {
		this.invitation_posts = invitation_posts;
	}
	public String getClub_Dateline() {
		return club_Dateline;
	}
	public void setClub_Dateline(String club_Dateline) {
		this.club_Dateline = club_Dateline;
	}
	public String getFounderUid() {
		return founderUid;
	}
	public void setFounderUid(String founderUid) {
		this.founderUid = founderUid;
	}
	public String getFounderName() {
		return founderName;
	}
	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}
	public String getFounderUrl() {
		return founderUrl;
	}
	public void setFounderUrl(String founderUrl) {
		this.founderUrl = founderUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public String getClub_LOGO() {
		return club_LOGO;
	}
	public void setClub_LOGO(String club_LOGO) {
		this.club_LOGO = club_LOGO;
	}
	public String getClub_brief() {
		return club_brief;
	}
	public void setClub_brief(String club_brief) {
		this.club_brief = club_brief;
	}
	public int getAddNumber() {
		return addNumber;
	}
	public void setAddNumber(int addNumber) {
		this.addNumber = addNumber;
	}
	
	public Club(int id, String club_name, String club_LOGO, String club_brief, int addNumber) {
		super();
		this.id = id;
		this.club_name = club_name;
		this.club_LOGO = club_LOGO;
		this.club_brief = club_brief;
		this.addNumber = addNumber;
	}
	public Club(String club_name, String club_LOGO, String club_brief, int addNumber) {
		super();
		this.club_name = club_name;
		this.club_LOGO = club_LOGO;
		this.club_brief = club_brief;
		this.addNumber = addNumber;
	}
	public Club() {
		super();
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", club_name=" + club_name + ", club_LOGO=" + club_LOGO + ", club_brief=" + club_brief
				+ ", addNumber=" + addNumber + ", club_location=" + club_location + ", club_type=" + club_type
				+ ", invitation_posts=" + invitation_posts + ", club_Dateline=" + club_Dateline + ", founderUid="
				+ founderUid + ", founderName=" + founderName + ", founderUrl=" + founderUrl + ", is_join=" + is_join
				+ "]";
	}
	
	
}
