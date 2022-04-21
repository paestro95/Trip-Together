package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.WishDAO;
import com.triptogether.vo.WishVO;

@Service
public class WishServiceImpl implements WishService{
	@Inject WishDAO dao;

	@Override
	public int wish(WishVO wish) {
		return dao.wish(wish);
	}

	@Override
	public int unwish(WishVO wish) {
		return dao.unwish(wish);
	}

	@Override
	public Integer oxWish(WishVO wish) {
		return dao.oxWish(wish);
	}

	@Override
	public List<WishVO> selectWishList(String id) {
		return dao.selectWishList(id);
	}

	@Override
	public Integer wishCnt(WishVO wish) {
		return dao.wishCnt(wish);
	}
	
}
