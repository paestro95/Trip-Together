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
	public List<CommunityVO> communityList(String id) {
		return dao.communityList(id);
	}
	
	@Override
	public List<CommunityVO> communityNewList(String id) {
		return dao.communityNewList(id);
	}

	@Override
	public CommunityVO communitySelect(int no) {
		// TODO Auto-generated method stub
		return dao.communitySelect(no);
	}

	@Override
	public int communityInsert(CommunityVO vo, String[] filePath) {
		return dao.communityInsert(vo, filePath);
	}

	@Override
	public int communityUpdate(CommunityVO vo, String[] filePath) {
		return dao.communityUpdate(vo, filePath);
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

	@Override
	public List<CommunityVO> hotPostSelect(String id) {
		// TODO Auto-generated method stub
		return dao.hotPostSelect(id);
	}

	@Override
	public List<CommunityVO> userPostAllSelect(String id) {
		// TODO Auto-generated method stub
		return dao.userPostAllSelect(id);
	}

	@Override
	public List<CommunityVO> firstPhotoSelect(String id) {
		return dao.firstPhotoSelect(id);
	}

	@Override
	public List<CommunityVO> bestPostSelect() {
		return dao.bestPostSelect();
	}

	@Override
	public List<CommunityVO> weeklyPostSelect() {
		return dao.weeklyPostSelect();
	}





}