package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.FollowVO;

@Mapper
@Repository
public interface FollowDAO {
	public int follow(FollowVO follow);	//팔로우하기
	public int unfollow(FollowVO follow); //언팔로우하기
	int oxFollow(FollowVO follow); // 팔로우 유무
	List<FollowVO> selectFollowingList(String follow_id);//팔로잉리스트 조회
	List<FollowVO> selectFollowerList(String follow_id);//팔로워 리스트 조회
	void deleteUserAllFollow(String follow_id);//탈퇴할때 팔로우 삭제
	
	//팔로잉 인원 count 받아오기
	public int followingCnt(String id);
	
	//팔로워 인원 count 받아오기
	public int followerCnt(String id);
}