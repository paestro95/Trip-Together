package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.WishVO;

public interface WishService {
	public int wish(WishVO wish);//����
	public int unwish(WishVO wish);//�������
	public Integer oxWish(WishVO wish);//�������� Ȯ��
	public Integer wishCnt(WishVO wish);
	public List<WishVO> selectWishList(String id);	//���ø���Ʈ �ҷ�����
}
