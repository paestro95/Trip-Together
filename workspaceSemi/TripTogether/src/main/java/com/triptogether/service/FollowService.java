package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.FollowVO;

public interface FollowService {
	public void follow(FollowVO follow);	//팔로우하기
	public void unfollow(FollowVO follow); //언팔로우하기
	int oxFollow(FollowVO follow); // 팔로우 유무
	List<FollowVO> selectFollowingList(int followingUser);//팔로잉리스트 조회
	List<FollowVO> selectFollowerList(int followerUser);//팔로워 리스트 조회
	void deleteUserAllFollow(int followingUser);//탈퇴할때 팔로우 삭제
}