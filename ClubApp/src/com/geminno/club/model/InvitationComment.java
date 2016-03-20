package com.geminno.club.model;

import java.util.Date;

/*
 * 帖子与评论用户表
 * */
public class InvitationComment {
	private int ic_id;//主键
	private Invitation invitation;//帖子对象  获得帖子id
	private User user;//评论人对象  获得评论人id
	private String content;//评论内容
	private Date create_date;//评论创建时间
	private InvitationComment(){}
	private InvitationComment(Invitation invitation, User user,
			String content, Date create_date) {
		super();
		this.invitation = invitation;
		this.user = user;
		this.content = content;
		this.create_date = create_date;
	}
	private InvitationComment(int ic_id, Invitation invitation, User user,
			String content, Date create_date) {
		super();
		this.ic_id = ic_id;
		this.invitation = invitation;
		this.user = user;
		this.content = content;
		this.create_date = create_date;
	}
	public int getIc_id() {
		return ic_id;
	}
	public void setIc_id(int ic_id) {
		this.ic_id = ic_id;
	}
	public Invitation getInvitation() {
		return invitation;
	}
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "InvitationComment [ic_id=" + ic_id + ", invitation="
				+ invitation + ", user=" + user + ", content=" + content
				+ ", create_date=" + create_date + "]";
	}
	
}
