package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.FollowVO;

public interface FollowService {
	public void follow(FollowVO follow);	//�ȷο��ϱ�
	public void unfollow(FollowVO follow); //���ȷο��ϱ�
	int oxFollow(FollowVO follow); // �ȷο� ����
	List<FollowVO> selectFollowingList(String follow_id);//�ȷ��׸���Ʈ ��ȸ
	List<FollowVO> selectFollowerList(String follow_id);//�ȷο� ����Ʈ ��ȸ
	void deleteUserAllFollow(String follow_id);//Ż���Ҷ� �ȷο� ����
}