package com.triptogether.vo;

public class NoticeVO {
    private int notice_no;            //공지사항 번호
    private String title;    //공지사항 제목
    private String id;    //공지사항 작성자(admin)
    private String writedate;    //공지사항 업로드 날짜
    private String content;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}     
}