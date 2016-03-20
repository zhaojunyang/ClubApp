package com.geminno.club.entity;

public class MyClubsInfo {
	
	private String name;
	private String icon;
	private String membernum;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMembernum() {
		return membernum;
	}
	public void setMembernum(String membernum) {
		this.membernum = membernum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MyClubsInfo(String name, String icon, String membernum,
			String description) {
		super();
		this.name = name;
		this.icon = icon;
		this.membernum = membernum;
		this.description = description;
	}
	public MyClubsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyClubsInfo [name=" + name + ", icon=" + icon + ", membernum="
				+ membernum + ", description=" + description + "]";
	}
	
	
}
