package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.FollowVO;

@Mapper
@Repository
public interface FollowDAO {
	public void follow(FollowVO follow);	//�ȷο��ϱ�
	public void unfollow(FollowVO follow); //���ȷο��ϱ�
	int oxFollow(FollowVO follow); // �ȷο� ����
	List<FollowVO> selectFollowingList(int followingUser);//�ȷ��׸���Ʈ ��ȸ
	List<FollowVO> selectFollowerList(int followerUser);//�ȷο� ����Ʈ ��ȸ
	void deleteUserAllFollow(int followingUser);//Ż���Ҷ� �ȷο� ����
}