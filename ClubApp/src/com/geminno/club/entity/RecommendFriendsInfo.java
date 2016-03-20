package com.geminno.club.entity;

public class RecommendFriendsInfo {
	private String uid;
	private String username;// 推荐用户名称
	private String status;// 状态
	private String dateline;
	private String reason;// 推荐理由
	private String opuid;// 操作用户id
	private String opusername;// 操作用户名称
	private String displayorder;
	private String is_friend;// 1 是否已关注,1已关注0未关注
	private String avatar;// 头像
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
	public String getDateline() {
		return dateline;
	}
	public void setDateline(String dateline) {
		this.dateline = dateline;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOpuid() {
		return opuid;
	}
	public void setOpuid(String opuid) {
		this.opuid = opuid;
	}
	public String getOpusername() {
		return opusername;
	}
	public void setOpusername(String opusername) {
		this.opusername = opusername;
	}
	public String getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
	}
	public String getIs_friend() {
		return is_friend;
	}
	public void setIs_friend(String is_friend) {
		this.is_friend = is_friend;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public RecommendFriendsInfo(String uid, String username, String status,
			String dateline, String reason, String opuid, String opusername,
			String displayorder, String is_friend, String avatar) {
		super();
		this.uid = uid;
		this.username = username;
		this.status = status;
		this.dateline = dateline;
		this.reason = reason;
		this.opuid = opuid;
		this.opusername = opusername;
		this.displayorder = displayorder;
		this.is_friend = is_friend;
		this.avatar = avatar;
	}
	public RecommendFriendsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RecommendFriendsInfo [uid=" + uid + ", username=" + username
				+ ", status=" + status + ", dateline=" + dateline + ", reason="
				+ reason + ", opuid=" + opuid + ", opusername=" + opusername
				+ ", displayorder=" + displayorder + ", is_friend=" + is_friend
				+ ", avatar=" + avatar + "]";
	}
	

}
