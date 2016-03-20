package com.geminno.club.entity;

/**
 * 
 * @author Administrator
 * 
 */
public class PeopleCenterInfo {

	private String uid;// id

	private String username;// 用户名

	private String status;// 用户状态 为0 是正常

	private String avatarstatus;// 头像状态 为0 是没有设置头像

	private String adminid;// 是否是管理员 为0 不是

	private String groupid;// 会员组id

	private String regdate;// 注册日期

	private String credits;// 总积分

	private String newpm;// 新短消息数量

	private String newprompt;// 新提醒数目

	private String friends;// 好友 数

	private String posts;// 回复 数

	private String threads;// 主题数

	private String avatar;// 头像uri

	private String grouptitle;// "新手上路" 会员组名称

	private String sightml;// 个人签名(最好用webview显示),

	private String occupation;// 职业

	private String resideprovince;// 居住省(提交名称)

	private String residecity;// 居住城市

	private String residedist;// 居住行政区/县

	private String interest;// 兴趣爱好

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAvatarstatus() {
		return avatarstatus;
	}

	public void setAvatarstatus(String avatarstatus) {
		this.avatarstatus = avatarstatus;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getNewpm() {
		return newpm;
	}

	public void setNewpm(String newpm) {
		this.newpm = newpm;
	}

	public String getNewprompt() {
		return newprompt;
	}

	public void setNewprompt(String newprompt) {
		this.newprompt = newprompt;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

	public String getThreads() {
		return threads;
	}

	public void setThreads(String threads) {
		this.threads = threads;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGrouptitle() {
		return grouptitle;
	}

	public void setGrouptitle(String grouptitle) {
		this.grouptitle = grouptitle;
	}

	public String getSightml() {
		return sightml;
	}

	public void setSightml(String sightml) {
		this.sightml = sightml;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getResideprovince() {
		return resideprovince;
	}

	public void setResideprovince(String resideprovince) {
		this.resideprovince = resideprovince;
	}

	public String getResidecity() {
		return residecity;
	}

	public void setResidecity(String residecity) {
		this.residecity = residecity;
	}

	public String getResidedist() {
		return residedist;
	}

	public void setResidedist(String residedist) {
		this.residedist = residedist;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public PeopleCenterInfo(String uid, String username, String status,
			String avatarstatus, String adminid, String groupid,
			String regdate, String credits, String newpm, String newprompt,
			String friends, String posts, String threads, String avatar,
			String grouptitle, String sightml, String occupation,
			String resideprovince, String residecity, String residedist,
			String interest) {
		super();
		this.uid = uid;
		this.username = username;
		this.status = status;
		this.avatarstatus = avatarstatus;
		this.adminid = adminid;
		this.groupid = groupid;
		this.regdate = regdate;
		this.credits = credits;
		this.newpm = newpm;
		this.newprompt = newprompt;
		this.friends = friends;
		this.posts = posts;
		this.threads = threads;
		this.avatar = avatar;
		this.grouptitle = grouptitle;
		this.sightml = sightml;
		this.occupation = occupation;
		this.resideprovince = resideprovince;
		this.residecity = residecity;
		this.residedist = residedist;
		this.interest = interest;
	}

	public PeopleCenterInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PeopleCenterInfo [uid=" + uid + ", username=" + username
				+ ", status=" + status + ", avatarstatus=" + avatarstatus
				+ ", adminid=" + adminid + ", groupid=" + groupid
				+ ", regdate=" + regdate + ", credits=" + credits + ", newpm="
				+ newpm + ", newprompt=" + newprompt + ", friends=" + friends
				+ ", posts=" + posts + ", threads=" + threads + ", avatar="
				+ avatar + ", grouptitle=" + grouptitle + ", sightml="
				+ sightml + ", occupation=" + occupation + ", resideprovince="
				+ resideprovince + ", residecity=" + residecity
				+ ", residedist=" + residedist + ", interest=" + interest + "]";
	}
	
	

}
