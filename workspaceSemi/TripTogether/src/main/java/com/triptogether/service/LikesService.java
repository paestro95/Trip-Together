package com.triptogether.service;

import com.triptogether.vo.LikesVO;

public interface LikesService {
		// 좋아요 추가
		public int likes(LikesVO likes);
		
		// 좋아요 취소
		public int unlikes(LikesVO likes);
		
		// 좋아요 유무
		public int oxLikes(LikesVO likes);
		
		// 좋아요 수
		public Integer likesCnt(LikesVO likes);
}
