package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.FollowDAO;
import com.triptogether.vo.FollowVO;

@Service
public class FollowServiceImpl implements FollowService{
	@Inject FollowDAO dao;
	private FollowDAO followDAO;

	@Override
	public void follow(FollowVO follow) {
		followDAO.follow(follow);
	}

	@Override
	public void unfollow(FollowVO follow) {
		followDAO.unfollow(follow);
	}

	@Override
	public int oxFollow(FollowVO follow) {
		return dao.oxFollow(follow);
	}

	@Override
	public List<FollowVO> selectFollowingList(int followingUser) {
		return dao.selectFollowingList(followingUser);
	}

	@Override
	public List<FollowVO> selectFollowerList(int followerUser) {
		return dao.selectFollowerList(followerUser);
	}

	@Override
	public void deleteUserAllFollow(int followingUser) {	
		return;
	}

}