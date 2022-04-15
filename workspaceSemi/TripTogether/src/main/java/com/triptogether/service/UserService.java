package com.triptogether.service;

import com.triptogether.vo.UserVO;

public interface UserService {
   //ȸ������ - joinForm>joinOk
   public int userInsert(UserVO vo);
   //���̵� �ߺ� üũ
   public int idCheck(String id);

   //ȸ���������� �� ����>�α��� ���� ��������
   public UserVO userSelect(String id);
   //ȸ������ ���� - userEdit>userEditOk
   public int userUpdate(UserVO vo);
   
   
   //�α��� (�ӽ�)
   public UserVO loginCheck(UserVO vo);
}