package com.triptogether.vo;
//id, info

//following_id, likes, wish
//photo
//tag

public class CommunityVO {
	private int no;
	private String title; // �Խù� ����
	private String photo; // �Խù� ����
	private String content; // �Խù� ����

	private int replyno; // ��� ��ȣ
	private String id; // �Խù� �ۼ���
	private String comment; // ���
	private String writedate; // ��� ��¥

	private String name; // ���̵� �̸�
	private String info; // ���� �Ұ�

	private String userid;// �ȷο�
	private String following_id;// �ȷ���
	private int likes; // ���ƿ�
	private int wish; // ���ã��

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFollowing_id() {
		return following_id;
	}

	public void setFollowing_id(String following_id) {
		this.following_id = following_id;
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

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	/*
	 * private String id;//Ÿ���� ���̵� ���̰� private String info; private String
	 * following_id; private int likes; private int wish; private String tag;
	 * 
	 * public String getId() { return id; } public void setId(String id) { this.id =
	 * id; } public String getInfo() { return info; } public void setInfo(String
	 * info) { this.info = info; } public String getFollowing_id() { return
	 * following_id; } public void setFollowing_id(String following_id) {
	 * this.following_id = following_id; } public int getLikes() { return likes; }
	 * public void setLikes(int likes) { this.likes = likes; } public int getWish()
	 * { return wish; } public void setWish(int wish) { this.wish = wish; } public
	 * String getTag() { return tag; } public void setTag(String tag) { this.tag =
	 * tag; }
	 */
}