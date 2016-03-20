package com.geminno.club.entity;

/**
 * 
 * @author Administrator
 * 
 */
public class MyactivityFieldInfo {
	//完整的标题
	private String fulltitle;
	//发起时间
	private String time;
	//截止时间
	private String expiration;
	//发起人
	private String author;
	//每人花销
	private String cost;
	//地点
	private String place;
	//活动类别
	private String classes;
	//性别
	private String gender;
	//人数
	private String number;
	//已参加人数
	private String applynumber;
	//俱乐部名字
	private String groupname;
	//俱乐部头像
	private String icon;
	//俱乐部id
	private String fid;
	
	private String dateline;
	private String lastpost;
	private String replies;
	private String views;
	private String heats;
	private String praises;
	private String recommends;
	private String stamp;
	public String getFulltitle() {
		return fulltitle;
	}
	public void setFulltitle(String fulltitle) {
		this.fulltitle = fulltitle;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getApplynumber() {
		return applynumber;
	}
	public void setApplynumber(String applynumber) {
		this.applynumber = applynumber;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getLastpost() {
		return lastpost;
	}
	public void setLastpost(String lastpost) {
		this.lastpost = lastpost;
	}
	public String getReplies() {
		return replies;
	}
	public void setReplies(String replies) {
		this.replies = replies;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getHeats() {
		return heats;
	}
	public void setHeats(String heats) {
		this.heats = heats;
	}
	public String getPraises() {
		return praises;
	}
	public void setPraises(String praises) {
		this.praises = praises;
	}
	public String getRecommends() {
		return recommends;
	}
	public void setRecommends(String recommends) {
		this.recommends = recommends;
	}
	public String getStamp() {
		return stamp;
	}
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	public MyactivityFieldInfo(String fulltitle, String time,
			String expiration, String author, String cost, String place,
			String classes, String gender, String number, String applynumber,
			String groupname, String icon, String fid, String dateline,
			String lastpost, String replies, String views, String heats,
			String praises, String recommends, String stamp) {
		super();
		this.fulltitle = fulltitle;
		this.time = time;
		this.expiration = expiration;
		this.author = author;
		this.cost = cost;
		this.place = place;
		this.classes = classes;
		this.gender = gender;
		this.number = number;
		this.applynumber = applynumber;
		this.groupname = groupname;
		this.icon = icon;
		this.fid = fid;
		this.dateline = dateline;
		this.lastpost = lastpost;
		this.replies = replies;
		this.views = views;
		this.heats = heats;
		this.praises = praises;
		this.recommends = recommends;
		this.stamp = stamp;
	}
	public MyactivityFieldInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyactivityFieldInfo [fulltitle=" + fulltitle + ", time=" + time
				+ ", expiration=" + expiration + ", author=" + author
				+ ", cost=" + cost + ", place=" + place + ", classes="
				+ classes + ", gender=" + gender + ", number=" + number
				+ ", applynumber=" + applynumber + ", groupname=" + groupname
				+ ", icon=" + icon + ", fid=" + fid + ", dateline=" + dateline
				+ ", lastpost=" + lastpost + ", replies=" + replies
				+ ", views=" + views + ", heats=" + heats + ", praises="
				+ praises + ", recommends=" + recommends + ", stamp=" + stamp
				+ "]";
	}
	
	

}
