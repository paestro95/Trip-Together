package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.FollowVO;

public interface FollowService {
	public void follow(FollowVO follow);	//�ȷο��ϱ�
	public void unfollow(FollowVO follow); //���ȷο��ϱ�
	int oxFollow(FollowVO follow); // �ȷο� ����
	List<FollowVO> selectFollowingList(int followingUser);//�ȷ��׸���Ʈ ��ȸ
	List<FollowVO> selectFollowerList(int followerUser);//�ȷο� ����Ʈ ��ȸ
	void deleteUserAllFollow(int followingUser);//Ż���Ҷ� �ȷο� ����
}