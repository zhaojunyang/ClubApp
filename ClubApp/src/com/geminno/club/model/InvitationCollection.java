package com.geminno.club.model;
/*
 * 帖子与收藏用户关系表
 * */
public class InvitationCollection {
	private int ic_id;//主键
	private Invitation invitation;//获得帖子id
	private User user;//获得收藏人id
	private InvitationCollection(){}
	private InvitationCollection(Invitation invitation, User user) {
		super();
		this.invitation = invitation;
		this.user = user;
	}
	private InvitationCollection(int ic_id, Invitation invitation, User user) {
		super();
		this.ic_id = ic_id;
		this.invitation = invitation;
		this.user = user;
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

	@Override
	public String toString() {
		return "InvitationCollection [ic_id=" + ic_id + ", invitation="
				+ invitation + ", user=" + user + "]";
	}
	
}
