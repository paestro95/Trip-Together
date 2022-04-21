package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.FollowVO;

@Mapper
@Repository
public interface FollowDAO {
	public int follow(FollowVO follow);	//�ȷο��ϱ�
	public int unfollow(FollowVO follow); //���ȷο��ϱ�
	int oxFollow(FollowVO follow); // �ȷο� ����
	List<FollowVO> selectFollowingList(String follow_id);//�ȷ��׸���Ʈ ��ȸ
	List<FollowVO> selectFollowerList(String follow_id);//�ȷο� ����Ʈ ��ȸ
	void deleteUserAllFollow(String follow_id);//Ż���Ҷ� �ȷο� ����
	
	//�ȷ��� �ο� count �޾ƿ���
	public int followingCnt(String id);
	
	//�ȷο� �ο� count �޾ƿ���
	public int followerCnt(String id);
}