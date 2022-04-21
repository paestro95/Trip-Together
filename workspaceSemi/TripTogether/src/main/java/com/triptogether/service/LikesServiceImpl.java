package com.triptogether.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.LikesDAO;
import com.triptogether.vo.LikesVO;

@Service
public class LikesServiceImpl  implements LikesService{
	@Inject 
	LikesDAO dao;

	@Override
	public int likes(LikesVO likes) {
		// TODO Auto-generated method stub
		return dao.likes(likes);
	}

	@Override
	public int oxLikes(LikesVO likes) {
		// TODO Auto-generated method stub
		return dao.oxLikes(likes);
	}

	@Override
	public int unlikes(LikesVO likes) {
		// TODO Auto-generated method stub
		return dao.unlikes(likes);
	}

	@Override
	public Integer likesCnt(LikesVO likes) {
		// TODO Auto-generated method stub
		return dao.likesCnt(likes);
	}
	
	
}
