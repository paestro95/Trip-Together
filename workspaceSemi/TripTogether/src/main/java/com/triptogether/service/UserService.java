package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.UserVO;

public interface UserService {
   //회원가입 - joinForm>joinOk
	public int userInsert(UserVO vo, String filePath);
   //아이디 중복 체크
   public int idCheck(String id);

   //회원정보수정 폼 진입>로그인 정보 가져오기
   public UserVO userSelect(String id);
   //회원정보 수정 - userEdit>userEditOk
   public int userUpdate(UserVO vo, String filePath);
   
   
   //로그인 (임시)
   public UserVO loginCheck(UserVO vo);
   public UserVO inquiryOfUserById(String id);
   
   //다른사람 프로필 불러다 보기 (userView)
   public UserVO userView(String id);
   
   //회원탈퇴	-- 220421 진영님 추가
   public int userDelete(String id);
   
   // 전체 멤버 선택하기
   public List<UserVO> userAllSelect();

   // 관리자 선택 회원삭제
   public int userListMultiDelete(UserVO vo);
}