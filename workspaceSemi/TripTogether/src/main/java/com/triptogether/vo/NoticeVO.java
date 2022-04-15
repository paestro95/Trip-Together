package com.triptogether.vo;

public class NoticeVO {
    private int no;            //공지사항 번호
    private String title;    //공지사항 제목
    private String id;    //공지사항 작성자 (ex.관리자1, 관리자2)
    private String writedate;    //공지사항 업로드 날짜
    private String content;
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
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
    
    
}