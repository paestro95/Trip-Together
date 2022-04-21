package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.CommunityVO;

public interface CommunityService {
	// 커뮤니티 목록
	public List<CommunityVO> communityList(String id);

	// 커뮤니티 리스트 신규순으로 불러오기
	public List<CommunityVO> communityNewList(String id);
	
	// 커뮤니티 게시물 1개 선택
	public CommunityVO communitySelect(int no);
	
	// 커뮤니티 글등록
	public int communityInsert(CommunityVO vo, String[] filePath);
	
	// 커뮤니티 글수정
	public int communityUpdate(CommunityVO vo, String[] filePath);
	
	// 커뮤니티 글삭제
	public int communityDelete(int no, String id);	
	
	// user의 인기 게시물 가져오기(4개)
	public List<CommunityVO> hotPostSelect(String id);
	
	// user의 모든 게시물 가져오기
	public List<CommunityVO> userPostAllSelect(String id);
	
	//해당ID 유저의 대표사진만 불러오기
	public List<CommunityVO> firstPhotoSelect(String id);
	
	// 이번주 인기 여행지 선택
    public List<CommunityVO> bestPostSelect();

    // 이번주 여행지 선택(3개)
    public List<CommunityVO> weeklyPostSelect();
}