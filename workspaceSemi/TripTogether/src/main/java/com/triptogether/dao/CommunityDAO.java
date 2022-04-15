package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.CommunityVO;

@Mapper
@Repository
public interface CommunityDAO {
	public List<CommunityVO> allSelect();
	public CommunityVO communitySelect(int no);
	public int communityInsert(CommunityVO vo);
	public int communityUpdate(CommunityVO vo);
	public int communityDelete(int no, String id);		
}