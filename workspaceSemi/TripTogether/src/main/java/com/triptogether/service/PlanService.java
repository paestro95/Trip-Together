package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.PlanVO;

public interface PlanService {
	//신규 plan 생성
	public int planCreate(PlanVO vo);
	
	//myplan에 리스트 뿌리기
	public List<PlanVO> planList(String id);
	
	//plan 삭제
	public int planDelete(int no, String id);
}
