package com.geminno.club.entity;

import java.util.ArrayList;

public class MyStoreInfo {
	private String favid;
	private String fav_dateline;
	private String id;
	private String idtype;
	private String title;
	private String url;
	private String pic;
	private String picflag;
	private String is_friend;
	private MyStoreFieldsInfo fields;
	public String getFavid() {
		return favid;
	}
	public void setFavid(String favid) {
		this.favid = favid;
	}
	public String getFav_dateline() {
		return fav_dateline;
	}
	public void setFav_dateline(String fav_dateline) {
		this.fav_dateline = fav_dateline;
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
	public String getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(String is_friend) {
		this.is_friend = is_friend;
	}
	public MyStoreFieldsInfo getFields() {
		return fields;
	}
	public void setFields(MyStoreFieldsInfo fields) {
		this.fields = fields;
	}
	public MyStoreInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyStoreInfo [favid=" + favid + ", fav_dateline=" + fav_dateline
				+ ", id=" + id + ", idtype=" + idtype + ", title=" + title
				+ ", url=" + url + ", pic=" + pic + ", picflag=" + picflag
				+ ", is_friend=" + is_friend + ", fields=" + fields + "]";
	}
	
	
}
