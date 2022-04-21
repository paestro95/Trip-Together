package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.WishVO;

public interface WishService {
	public int wish(WishVO wish);//위시
	public int unwish(WishVO wish);//위시취소
	public Integer oxWish(WishVO wish);//위시인지 확인
	public Integer wishCnt(WishVO wish);
	public List<WishVO> selectWishList(String id);	//위시리스트 불러오기
}
