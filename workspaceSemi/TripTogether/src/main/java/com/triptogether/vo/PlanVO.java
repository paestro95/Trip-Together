package com.triptogether.vo;

public class PlanVO {
	private String id;
	private String start_date;
	private String end_date;
	private String location;
	private int plan_no;
	private String writedate;
	
	//�и��� location ���ڿ� �޾� ���� �迭
	private String[] splitLoc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(int plan_no) {
		this.plan_no = plan_no;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	
	
	//location ���ڿ� �и��Ͽ� ����
	public String[] getSplitLoc() {
		this.location = this.location.replace("/", "");
		this.splitLoc = this.location.split(",");
		
		return this.splitLoc;
	}
	
}
