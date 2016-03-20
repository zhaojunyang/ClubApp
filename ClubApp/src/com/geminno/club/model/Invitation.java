package com.geminno.club.model;

import java.util.Date;

/*
 * 帖子表
 * */
public class Invitation {
	private int invitation_id;//主键
	private Forum forum ;//帖子所属论坛对象  获得论坛id
	private User user; //用户对象    获得用户id
	private String title;//帖子标题
	private String content;//帖子内容
	private String photo_URL;//图片URL
	private Date create_date;//帖子创建时间
	private int reply_count ; // 回复人数
	private int like_count;  //点赞人数

	private Invitation(){}
	private Invitation( Forum forum, User user, String title,
			String content, String photo_URL, Date create_date) {
		super();
		this.forum = forum;
		this.user = user;
		this.title = title;
		this.content = content;
		this.photo_URL = photo_URL;
		this.create_date = create_date;
	}
	private Invitation(int invitation_id, Forum forum, User user, String title,
			String content, String photo_URL, Date create_date) {
		super();
		this.invitation_id = invitation_id;
		this.forum = forum;
		this.user = user;
		this.title = title;
		this.content = content;
		this.photo_URL = photo_URL;
		this.create_date = create_date;
	}
	public int getInvitation_id() {
		return invitation_id;
	}
	public void setInvitation_id(int invitation_id) {
		this.invitation_id = invitation_id;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoto_URL() {
		return photo_URL;
	}
	public void setPhoto_URL(String photo_URL) {
		this.photo_URL = photo_URL;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "Invitation [invitation_id=" + invitation_id + ", forum="
				+ forum + ", user=" + user + ", title=" + title + ", content="
				+ content + ", photo_URL=" + photo_URL + ", create_date="
				+ create_date + "]";
	}
	public Invitation(User user, String title, String photo_URL, int reply_count, int like_count) {
		super();
		this.user = user;
		this.title = title;
		this.photo_URL = photo_URL;
		this.reply_count = reply_count;
		this.like_count = like_count;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
}
