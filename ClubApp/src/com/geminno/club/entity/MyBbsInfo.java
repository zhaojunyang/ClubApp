package com.geminno.club.entity;

public class MyBbsInfo {

	private String tid;
	private String fid;
	private String author;
	private String authorid;
	private String subject;
	private String dateline;
	private String views;
	private String replies;
	private String praises;
	private String special;
	private String dbdateline;
	private String name;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getReplies() {
		return replies;
	}
	public void setReplies(String replies) {
		this.replies = replies;
	}
	public String getPraises() {
		return praises;
	}
	public void setPraises(String praises) {
		this.praises = praises;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getDbdateline() {
		return dbdateline;
	}
	public void setDbdateline(String dbdateline) {
		this.dbdateline = dbdateline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MyBbsInfo(String tid, String fid, String author, String authorid,
			String subject, String dateline, String views, String replies,
			String praises, String special, String dbdateline, String name) {
		super();
		this.tid = tid;
		this.fid = fid;
		this.author = author;
		this.authorid = authorid;
		this.subject = subject;
		this.dateline = dateline;
		this.views = views;
		this.replies = replies;
		this.praises = praises;
		this.special = special;
		this.dbdateline = dbdateline;
		this.name = name;
	}
	public MyBbsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyBbsInfo [tid=" + tid + ", fid=" + fid + ", author=" + author
				+ ", authorid=" + authorid + ", subject=" + subject
				+ ", dateline=" + dateline + ", views=" + views + ", replies="
				+ replies + ", praises=" + praises + ", special=" + special
				+ ", dbdateline=" + dbdateline + ", name=" + name + "]";
	}

	
	
	
}
