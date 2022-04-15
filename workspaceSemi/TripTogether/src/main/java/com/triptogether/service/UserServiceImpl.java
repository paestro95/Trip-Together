package com.triptogether.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.UserDAO;
import com.triptogether.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
   @Inject
   UserDAO dao;

   @Override
   public int userInsert(UserVO vo) {
      return dao.userInsert(vo);
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
   public int userUpdate(UserVO vo) {
      return dao.userUpdate(vo);
   }

   @Override
   public UserVO loginCheck(UserVO vo) {
      return dao.loginCheck(vo);
   }

   

}