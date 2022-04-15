package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.CommunityDAO;
import com.triptogether.vo.CommunityVO;

@Service
public class CommunityServiceImpl implements CommunityService {
	@Inject CommunityDAO dao;
	@Override
	public List<CommunityVO> allSelect() {
		return dao.allSelect();
	}

	@Override
	public CommunityVO communitySelect(int no) {
		return dao.communitySelect(no);
	}

	@Override
	public int communityInsert(CommunityVO vo) {
		return dao.communityInsert(vo);
	}

	@Override
	public int communityUpdate(CommunityVO vo) {
		return dao.communityUpdate(vo);
	}

	@Override
	public int communityDelete(int no, String id) {
		return dao.communityDelete(no, id);
	}
	/*
	 * @Inject CommunityDAO dao;
	 * 
	 * @Override public List<CommunityVO> allSelect() { return dao.allSelect(); }
	 * 
	 * @Override public CommunityVO communitySelect(int id) { return
	 * dao.communitySelect(id); }
	 */
}