package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.PlanVO;

public interface PlanService {
	//�ű� plan ����
	public int planCreate(PlanVO vo);
	
	//myplan�� ����Ʈ �Ѹ���
	public List<PlanVO> planList(String id);
	
	//plan ����
	public int planDelete(int no, String id);
}
