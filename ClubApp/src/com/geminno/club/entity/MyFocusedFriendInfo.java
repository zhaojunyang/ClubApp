package com.geminno.club.entity;

public class MyFocusedFriendInfo {
	private String uid;// 当前用户
	private String fuid;// 好友用户
	private String fusername;// 好友名称
	private String gid;//  好友所在的好友组
	private String num;// 好友之间的任务关系数
	private String dateline;// 加好友的时间戳
	private String note;// 好友备注
	private String isfriend; //是否是好友 1是 0不是
	private String avatar;//-好友头像
	private String thread_subject_last;//好友最新发布的一个主题
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFuid() {
		return fuid;
	}
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIsfriend() {
		return isfriend;
	}
	public void setIsfriend(String isfriend) {
		this.isfriend = isfriend;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getThread_subject_last() {
		return thread_subject_last;
	}
	public void setThread_subject_last(String thread_subject_last) {
		this.thread_subject_last = thread_subject_last;
	}
	public MyFocusedFriendInfo(String uid, String fuid, String fusername,
			String gid, String num, String dateline, String note,
			String isfriend, String avatar, String thread_subject_last) {
		super();
		this.uid = uid;
		this.fuid = fuid;
		this.fusername = fusername;
		this.gid = gid;
		this.num = num;
		this.dateline = dateline;
		this.note = note;
		this.isfriend = isfriend;
		this.avatar = avatar;
		this.thread_subject_last = thread_subject_last;
	}
	public MyFocusedFriendInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyFocusedFriendInfo [uid=" + uid + ", fuid=" + fuid
				+ ", fusername=" + fusername + ", gid=" + gid + ", num=" + num
				+ ", dateline=" + dateline + ", note=" + note + ", isfriend="
				+ isfriend + ", avatar=" + avatar + ", thread_subject_last="
				+ thread_subject_last + "]";
	}
	
}
