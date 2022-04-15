package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.FollowVO;

@Mapper
@Repository
public interface FollowDAO {
	public void follow(FollowVO follow);	//팔로우하기
	public void unfollow(FollowVO follow); //언팔로우하기
	int oxFollow(FollowVO follow); // 팔로우 유무
	List<FollowVO> selectFollowingList(int followingUser);//팔로잉리스트 조회
	List<FollowVO> selectFollowerList(int followerUser);//팔로워 리스트 조회
	void deleteUserAllFollow(int followingUser);//탈퇴할때 팔로우 삭제
}