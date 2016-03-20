package com.geminno.club.model;

import java.util.Date;
/*动态表*/
public class Dynamic {
  private int dynamic_id;//主键
  private User  user;//获取发布人id
  private String  content;//动态内容
  private String photo_URL;//插图URL
  private Date create_date;//发布时间
  private int praise_num;//动态点赞数
	  private Dynamic(){}
	  private Dynamic( User user, String content, String photo_URL,
			  Date create_date, int praise_num) {
		  super();
		  this.user = user;
		  this.content = content;
		  this.photo_URL = photo_URL;
		  this.create_date = create_date;
		  this.praise_num = praise_num;
	  }
	private Dynamic(int dynamic_id, User user, String content, String photo_URL,
			Date create_date, int praise_num) {
		super();
		this.dynamic_id = dynamic_id;
		this.user = user;
		this.content = content;
		this.photo_URL = photo_URL;
		this.create_date = create_date;
		this.praise_num = praise_num;
	}
	public int getDynamic_id() {
		return dynamic_id;
	}
	public void setDynamic_id(int dynamic_id) {
		this.dynamic_id = dynamic_id;
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
	public int getPraise_num() {
		return praise_num;
	}
	public void setPraise_num(int praise_num) {
		this.praise_num = praise_num;
	}
	@Override
	public String toString() {
		return "Dynamic [dynamic_id=" + dynamic_id + ", user=" + user
				+ ", content=" + content + ", photo_URL=" + photo_URL
				+ ", create_date=" + create_date + ", praise_num=" + praise_num
				+ "]";
	}
	
}
