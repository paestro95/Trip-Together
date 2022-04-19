package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.PlanDAO;
import com.triptogether.vo.PlanVO;

@Service
public class PlanServiceImpl implements PlanService {
	@Inject
	PlanDAO dao;
	

	@Override
	public int planCreate(PlanVO vo) {
		return dao.planCreate(vo);
	}


	@Override
	public List<PlanVO> planList(String id) {
		return dao.planList(id);
	}


	@Override
	public int planDelete(int no, String id) {
		return dao.planDelete(no, id);
	}

}
