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
	List<FollowVO> selectFollowingList(String follow_id);//�ȷ��׸���Ʈ ��ȸ
	List<FollowVO> selectFollowerList(String follow_id);//�ȷο� ����Ʈ ��ȸ
	void deleteUserAllFollow(String follow_id);//Ż���Ҷ� �ȷο� ����
}