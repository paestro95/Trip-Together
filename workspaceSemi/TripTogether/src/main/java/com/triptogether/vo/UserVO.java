package com.triptogether.vo;

public class UserVO {
   private String id;
   private String pwd;
   private String name;
   private String tel;
   private String email;
   private int gender;      //1: man, 2: woman
   private String info;
   private String joindate;
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPwd() {
      return pwd;
   }
   public void setPwd(String pwd) {
      this.pwd = pwd;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getTel() {
      return tel;
   }
   public void setTel(String tel) {
      this.tel = tel;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public int getGender() {
      return gender;
   }
   public void setGender(int gender) {
      this.gender = gender;
   }
   public String getInfo() {
      return info;
   }
   public void setInfo(String info) {
      this.info = info;
   }
   public String getJoindate() {
      return joindate;
   }
   public void setJoindate(String joindate) {
      this.joindate = joindate;
   }
   
   
}