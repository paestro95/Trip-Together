package com.triptogether.service;

import com.triptogether.vo.UserVO;

public interface UserService {
   //ȸ������ - joinForm>joinOk
	public int userInsert(UserVO vo, String filePath);
   //���̵� �ߺ� üũ
   public int idCheck(String id);

   //ȸ���������� �� ����>�α��� ���� ��������
   public UserVO userSelect(String id);
   //ȸ������ ���� - userEdit>userEditOk
   public int userUpdate(UserVO vo, String filePath);
   
   
   //�α��� (�ӽ�)
   public UserVO loginCheck(UserVO vo);
   public UserVO inquiryOfUserById(String id);
   
   //�ٸ���� ������ �ҷ��� ���� (userView)
   public UserVO userView(String id);
   
   //ȸ��Ż��	-- 220421 ������ �߰�
   public int userDelete(String id);
}