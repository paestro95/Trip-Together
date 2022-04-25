package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.UserDAO;
import com.triptogether.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
   @Inject
   UserDAO dao;

   @Override
   public int userInsert(UserVO vo, String filePath) {
      return dao.userInsert(vo, filePath);
   }

   @Override
   public int idCheck(String id) {
      return dao.idCheck(id);
   }
   
   @Override
   public UserVO userSelect(String id) {
      return dao.userSelect(id);
   }

   @Override
   public int userUpdate(UserVO vo, String filePath) {
      return dao.userUpdate(vo, filePath);
   }

   @Override
   public UserVO loginCheck(UserVO vo) {
      return dao.loginCheck(vo);
   }
   
   @Override
   public UserVO inquiryOfUserById(String id) {
       return dao.inquiryOfUserById(id);
   }

	@Override
	public UserVO userView(String id) {
		return dao.userView(id);
	}

	@Override
	public int userDelete(String id) {
		return dao.userDelete(id);
	}

	@Override
	public List<UserVO> userAllSelect() {
		return dao.userAllSelect();
	}
	
	@Override
	public int userListMultiDelete(UserVO vo) {	
		return dao.userListMultiDelete(vo);
	}
   
	

}