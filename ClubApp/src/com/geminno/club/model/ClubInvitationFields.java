package com.geminno.club.model;

public class ClubInvitationFields {
	
	private String fulltitle;//标题全称
	private String icon;//俱乐部图标
	private String author;//发布者名称
	private String avatar;//小头像
	private String avatar_middle;//中头像
	private String avatar_big;//大头像
	private String dateline;//发布时间
	private String lastpost;//最后更新时间
	private int posts;//发帖数
	private int todayposts;//今日发帖数量
	private String replies;//今日回复数量
	private String views;//查看数量
	private String praises;//点赞数量
	private String heats;//主题热度
	private String recommends;//推荐指数
	private String groupname;//组名
	private int fid;//俱乐部ID
	private String groupurl;
	public String getFulltitle() {
		return fulltitle;
	}
	public void setFulltitle(String fulltitle) {
		this.fulltitle = fulltitle;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAvatar_middle() {
		return avatar_middle;
	}
	public void setAvatar_middle(String avatar_middle) {
		this.avatar_middle = avatar_middle;
	}
	public String getAvatar_big() {
		return avatar_big;
	}
	public void setAvatar_big(String avatar_big) {
		this.avatar_big = avatar_big;
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
	public int getPosts() {
		return posts;
	}
	public void setPosts(int posts) {
		this.posts = posts;
	}
	public int getTodayposts() {
		return todayposts;
	}
	public void setTodayposts(int todayposts) {
		this.todayposts = todayposts;
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
	public String getPraises() {
		return praises;
	}
	public void setPraises(String praises) {
		this.praises = praises;
	}
	public String getHeats() {
		return heats;
	}
	public void setHeats(String heats) {
		this.heats = heats;
	}
	public String getRecommends() {
		return recommends;
	}
	public void setRecommends(String recommends) {
		this.recommends = recommends;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getGroupurl() {
		return groupurl;
	}
	public void setGroupurl(String groupurl) {
		this.groupurl = groupurl;
	}
	public ClubInvitationFields(String fulltitle, String icon, String author, String avatar, String avatar_middle,
			String avatar_big, String dateline, String lastpost, int posts, int todayposts, String replies,
			String views, String praises, String heats, String recommends, String groupname, int fid, String groupurl) {
		super();
		this.fulltitle = fulltitle;
		this.icon = icon;
		this.author = author;
		this.avatar = avatar;
		this.avatar_middle = avatar_middle;
		this.avatar_big = avatar_big;
		this.dateline = dateline;
		this.lastpost = lastpost;
		this.posts = posts;
		this.todayposts = todayposts;
		this.replies = replies;
		this.views = views;
		this.praises = praises;
		this.heats = heats;
		this.recommends = recommends;
		this.groupname = groupname;
		this.fid = fid;
		this.groupurl = groupurl;
	}
	public ClubInvitationFields() {
		super();
	}
	@Override
	public String toString() {
		return "ClubInvitationFields [fulltitle=" + fulltitle + ", icon=" + icon + ", author=" + author + ", avatar="
				+ avatar + ", avatar_middle=" + avatar_middle + ", avatar_big=" + avatar_big + ", dateline=" + dateline
				+ ", lastpost=" + lastpost + ", posts=" + posts + ", todayposts=" + todayposts + ", replies=" + replies
				+ ", views=" + views + ", praises=" + praises + ", heats=" + heats + ", recommends=" + recommends
				+ ", groupname=" + groupname + ", fid=" + fid + ", groupurl=" + groupurl + "]";
	}
	
	
	
	
}
