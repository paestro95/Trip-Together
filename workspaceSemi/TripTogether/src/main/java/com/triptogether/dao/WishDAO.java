package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.WishVO;

@Mapper
@Repository
public interface WishDAO {
	public int wish(WishVO wish);
	public int unwish(WishVO wish);
	public int oxWish(WishVO wish);
	public Integer wishCnt(WishVO wish);
	public List<WishVO> selectWishList(String id);	
}
