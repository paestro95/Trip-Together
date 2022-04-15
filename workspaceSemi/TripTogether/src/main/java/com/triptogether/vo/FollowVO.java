package com.triptogether.vo;

import java.util.Date;

public class FollowVO {
	private int followNo;
	private int followingUser;
	private int followerUser;
	private Date regDate;
	
	private String followingId;
	private String followeId;
	
	private String profileName;

	public int getFollowNo() {
		return followNo;
	}

	public void setFollowNo(int followNo) {
		this.followNo = followNo;
	}

	public int getFollowingUser() {
		return followingUser;
	}

	public void setFollowingUser(int followingUser) {
		this.followingUser = followingUser;
	}

	public int getFollowerUser() {
		return followerUser;
	}

	public void setFollowerUser(int followerUser) {
		this.followerUser = followerUser;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getFolloweId() {
		return followeId;
	}

	public void setFolloweId(String followeId) {
		this.followeId = followeId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	
}