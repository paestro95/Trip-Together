package com.triptogether.vo;

import java.util.List;

//id, info

//following_id, likes, wish
//photo
//tag

public class CommunityVO {
	private String id;
	private int board_no;
	
	private String region;
	private String theme;
	private String title;
	private String tags;
	//private String photo;

	private String location1;
	private String location_addr1;
	private String content1;
	private String photo1;
	
	private String location2;
	private String location_addr2;
	private String content2;
	private String photo2;
	
	private String location3;
	private String location_addr3;
	private String content3;
	private String photo3;
	
	private String location4;
	private String location_addr4;
	private String content4;
	private String photo4;
	
	
	private String writedate;
	private int likes;
	private int wish;
	
	private String likesid;
    private String wishid;
	
	private String info;
	private int comment;
	
	
	/*220419 오어진 추가*/
	private String user_img;
	
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	
	
	
	
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	/*
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}*/
	public String getLocation1() {
		return location1;
	}
	public void setLocation1(String location1) {
		this.location1 = location1;
	}
	public String getLocation_addr1() {
		return location_addr1;
	}
	public void setLocation_addr1(String location_addr1) {
		this.location_addr1 = location_addr1;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getLocation2() {
		return location2;
	}
	public void setLocation2(String location2) {
		this.location2 = location2;
	}
	public String getLocation_addr2() {
		return location_addr2;
	}
	public void setLocation_addr2(String location_addr2) {
		this.location_addr2 = location_addr2;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getLocation3() {
		return location3;
	}
	public void setLocation3(String location3) {
		this.location3 = location3;
	}
	public String getLocation_addr3() {
		return location_addr3;
	}
	public void setLocation_addr3(String location_addr3) {
		this.location_addr3 = location_addr3;
	}
	public String getContent3() {
		return content3;
	}
	public void setContent3(String content3) {
		this.content3 = content3;
	}	
	public String getLocation4() {
		return location4;
	}
	public void setLocation4(String location4) {
		this.location4 = location4;
	}
	public String getLocation_addr4() {
		return location_addr4;
	}
	public void setLocation_addr4(String location_addr4) {
		this.location_addr4 = location_addr4;
	}
	public String getContent4() {
		return content4;
	}
	public void setContent4(String content4) {
		this.content4 = content4;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getWish() {
		return wish;
	}
	public void setWish(int wish) {
		this.wish = wish;
	}
	
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getPhoto4() {
		return photo4;
	}
	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}
	public String getLikesid() {
		return likesid;
	}
	public void setLikesid(String likesid) {
		this.likesid = likesid;
	}
	public String getWishid() {
		return wishid;
	}
	public void setWishid(String wishid) {
		this.wishid = wishid;
	}

	
}