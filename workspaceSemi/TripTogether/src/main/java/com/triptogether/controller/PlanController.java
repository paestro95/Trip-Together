package com.triptogether.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.PlanService;
import com.triptogether.vo.PlanVO;
import com.triptogether.vo.UserVO;

@Controller
@RequestMapping("/plan/")
public class PlanController {
	
	@Inject
	PlanService service;
	
	//������ �̵� : '���� ������ �����?!'
	@GetMapping("planSearch")
	public String planSearch() {
		return "plan/planSearch";
	}

	//������ �̵� : plan�����-����(��ǥ)
	@GetMapping("planCreate/{loc}&{x}&{y}")
	public String planCreate(@PathVariable String loc, @PathVariable float x, @PathVariable float y, Model model) {
		model.addAttribute("loc", loc);
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "plan/planCreate";
	}
	
	//plan����
	@PostMapping("planCreateOk")
	public ResponseEntity<String> planCreateOk(PlanVO vo, HttpSession session, HttpServletRequest req) {
		
		vo.setId((String)req.getSession().getAttribute("logId"));
		
		ResponseEntity<String> entity = null;	// �����Ϳ� ó�����¸� ������
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			String[] locations = req.getParameterValues("location");
			service.planCreate(vo);
			
			//���� ����
			String msg = "<script>";
			msg += "alert('plan�� ��ϵǾ����ϴ�.');";
			msg += "location.href='/mypage/myPlan';";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch(Exception e) {			//��� ���� ��
			e.printStackTrace();
			String msg = "<script>";
			msg += "alert('plan ��Ͽ� �����Ͽ����ϴ�..');";
			msg += "location.href=history.back();";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
	
	
}