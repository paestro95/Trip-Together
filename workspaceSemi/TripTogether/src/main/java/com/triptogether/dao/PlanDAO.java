package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.PlanVO;

@Mapper
@Repository
public interface PlanDAO {
	//�ű� plan ����
	public int planCreate(PlanVO vo);
	
	//myplan�� ����Ʈ �Ѹ���
	public List<PlanVO> planList(String id);
	
	//plan ����
	public int planDelete(int no, String id);
}
