package com.triptogether.vo;

public class NoticeVO {
    private int no;            //�������� ��ȣ
    private String title;    //�������� ����
    private String id;    //�������� �ۼ��� (ex.������1, ������2)
    private String writedate;    //�������� ���ε� ��¥
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