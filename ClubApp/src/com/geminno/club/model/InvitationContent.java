package com.geminno.club.model;

import java.util.List;

public class InvitationContent {
	private String id;//帖子id
	private String idtype;//id的类型
	private String title;//帖子的标题（缩减的）
	private String url ;//
	private String pic;//帖子图片
	private String picflag;//
	private int is_friend;//是否关注
	private ClubInvitationFields fields;//帖内容
	public InvitationContent(String id, String idtype, String title, String url, String pic, String picflag,
			int is_friend, ClubInvitationFields fields) {
		super();
		this.id = id;
		this.idtype = idtype;
		this.title = title;
		this.url = url;
		this.pic = pic;
		this.picflag = picflag;
		this.is_friend = is_friend;
		this.fields = fields;
	}
	public InvitationContent() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPicflag() {
		return picflag;
	}
	public void setPicflag(String picflag) {
		this.picflag = picflag;
	}
	public int getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(int is_friend) {
		this.is_friend = is_friend;
	}
	public ClubInvitationFields getFields() {
		return fields;
	}
	public void setFields(ClubInvitationFields fields) {
		this.fields = fields;
	}
	
	
	
}
