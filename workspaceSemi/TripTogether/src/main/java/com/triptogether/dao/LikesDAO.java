package com.triptogether.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.LikesVO;

@Mapper
@Repository
public interface LikesDAO {
	// 좋아요 추가
	public int likes(LikesVO likes);
	
	// 좋아요 취소
	public int unlikes(LikesVO likes);
	
	// 좋아요 유무
	public int oxLikes(LikesVO likes);
	
	// 좋아요 수
	public Integer likesCnt(LikesVO likes);
	
	

}
