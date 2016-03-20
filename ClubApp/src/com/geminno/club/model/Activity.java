package com.geminno.club.model;

import java.math.BigDecimal;
import java.util.Date;
//活动表
public class Activity {
	private int ac_id;//主键
	private Club club ;//获得所属俱乐部id
	private User user;//获得活动发起人id
	private ActivityType activity_type;//获得活动类型id
	private String title;//活动主题
	private Date deadline;//活动报名结束时间
	private Date start_date ;//活动开始时间
	private Date end_date ;//活动结束时间
	private String place;//活动地点
	private String detail;//活动详情
	private int max_member;//最大允许报名人数
	private String charge;//活动是否收费
	private BigDecimal expenses;//收费金额
	private String photo_url;//活动封面URL
	private String need_verify;//报名是否需要审核
	private String reg_notice;//报名须知
	private String app_type;//活动报名类型，0所有人都可以报名 1俱乐部成员才可以报名
	private int like_count;
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public Activity(){}
	private Activity(Club club, User user,
			ActivityType activity_type, String title, Date deadline,
			Date start_date, Date end_date, String place, String detail,
			int max_member, String charge, BigDecimal expenses,
			String photo_url, String need_verify, String reg_notice,
			String app_type) {
		super();
		this.club = club;
		this.user = user;
		this.activity_type = activity_type;
		this.title = title;
		this.deadline = deadline;
		this.start_date = start_date;
		this.end_date = end_date;
		this.place = place;
		this.detail = detail;
		this.max_member = max_member;
		this.charge = charge;
		this.expenses = expenses;
		this.photo_url = photo_url;
		this.need_verify = need_verify;
		this.reg_notice = reg_notice;
		this.app_type = app_type;
	}
	public Activity(User user, String title, int max_member, String photo_url, int like_count) {
		super();
		this.user = user;
		this.title = title;
		this.max_member = max_member;
		this.photo_url = photo_url;
		this.like_count = like_count;
	}
	private Activity(int ac_id, Club club, User user,
			ActivityType activity_type, String title, Date deadline,
			Date start_date, Date end_date, String place, String detail,
			int max_member, String charge, BigDecimal expenses,
			String photo_url, String need_verify, String reg_notice,
			String app_type) {
		super();
		this.ac_id = ac_id;
		this.club = club;
		this.user = user;
		this.activity_type = activity_type;
		this.title = title;
		this.deadline = deadline;
		this.start_date = start_date;
		this.end_date = end_date;
		this.place = place;
		this.detail = detail;
		this.max_member = max_member;
		this.charge = charge;
		this.expenses = expenses;
		this.photo_url = photo_url;
		this.need_verify = need_verify;
		this.reg_notice = reg_notice;
		this.app_type = app_type;
	}
	public int getAc_id() {
		return ac_id;
	}
	public void setAc_id(int ac_id) {
		this.ac_id = ac_id;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ActivityType getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(ActivityType activity_type) {
		this.activity_type = activity_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getMax_member() {
		return max_member;
	}
	public void setMax_member(int max_member) {
		this.max_member = max_member;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public BigDecimal getExpenses() {
		return expenses;
	}
	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getNeed_verify() {
		return need_verify;
	}
	public void setNeed_verify(String need_verify) {
		this.need_verify = need_verify;
	}
	public String getReg_notice() {
		return reg_notice;
	}
	public void setReg_notice(String reg_notice) {
		this.reg_notice = reg_notice;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	@Override
	public String toString() {
		return "Activity [ac_id=" + ac_id + ", club=" + club + ", user=" + user
				+ ", activity_type=" + activity_type + ", title=" + title
				+ ", deadline=" + deadline + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", place=" + place + ", detail="
				+ detail + ", max_member=" + max_member + ", charge=" + charge
				+ ", expenses=" + expenses + ", photo_url=" + photo_url
				+ ", need_verify=" + need_verify + ", reg_notice=" + reg_notice
				+ ", app_type=" + app_type + "]";
	}
	
}

