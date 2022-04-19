package com.triptogether.vo;

import java.util.Date;

public class FollowVO {	
	private String id;
	private String follow_id;
	private Date regDate;
	
	//private int ? String? 
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFollow_id() {
		return follow_id;
	}
	public void setFollow_id(String follow_id) {
		this.follow_id = follow_id;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}		
}