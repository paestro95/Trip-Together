package com.triptogether.vo;

public class NoticeVO {
    private int notice_no;            //�������� ��ȣ
    private String title;    //�������� ����
    private String id;    //�������� �ۼ���(admin)
    private String writedate;    //�������� ���ε� ��¥
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