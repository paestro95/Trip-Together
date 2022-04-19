package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.PlanVO;

@Mapper
@Repository
public interface PlanDAO {
	//신규 plan 생성
	public int planCreate(PlanVO vo);
	
	//myplan에 리스트 뿌리기
	public List<PlanVO> planList(String id);
	
	//plan 삭제
	public int planDelete(int no, String id);
}
