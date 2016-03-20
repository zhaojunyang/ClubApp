package com.geminno.club.entity;

public class MyactivityInfo {

	private String id;
	private String idtype;
	private String title;
	private String pic;
	private String picflag;
	private MyactivityFieldInfo fields;
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
	public MyactivityFieldInfo getFields() {
		return fields;
	}
	public void setFields(MyactivityFieldInfo fields) {
		this.fields = fields;
	}
	public MyactivityInfo(String id, String idtype, String title, String pic,
			String picflag, MyactivityFieldInfo fields) {
		super();
		this.id = id;
		this.idtype = idtype;
		this.title = title;
		this.pic = pic;
		this.picflag = picflag;
		this.fields = fields;
	}
	public MyactivityInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyactivityInfo [id=" + id + ", idtype=" + idtype + ", title="
				+ title + ", pic=" + pic + ", picflag=" + picflag + ", fields="
				+ fields + "]";
	}

	
}
