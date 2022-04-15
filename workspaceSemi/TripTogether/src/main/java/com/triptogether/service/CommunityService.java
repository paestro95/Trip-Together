package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.CommunityVO;

public interface CommunityService {
	public List<CommunityVO> allSelect();
	public CommunityVO communitySelect(int no);
	public int communityInsert(CommunityVO vo);
	public int communityUpdate(CommunityVO vo);
	public int communityDelete(int no, String id);	
}