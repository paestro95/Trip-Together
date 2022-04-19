package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.FollowDAO;
import com.triptogether.vo.FollowVO;

@Service
public class FollowServiceImpl implements FollowService{
	@Inject FollowDAO dao;
	
	@Override
	public void follow(FollowVO follow) {
		dao.follow(follow);
	}
	@Override
	public void unfollow(FollowVO follow) {
		dao.unfollow(follow);	
	}
	@Override
	public int oxFollow(FollowVO follow) {	
		return dao.oxFollow(follow);
	}
	@Override
	public List<FollowVO> selectFollowingList(String follow_id) {	
		return dao.selectFollowingList(follow_id);
	}
	@Override
	public List<FollowVO> selectFollowerList(String follow_id) {
		return dao.selectFollowerList(follow_id);
	}
	@Override
	public void deleteUserAllFollow(String follow_id) {
		
	}

	
}