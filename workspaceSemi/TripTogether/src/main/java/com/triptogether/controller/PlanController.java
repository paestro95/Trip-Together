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
	
	//페이지 이동 : '어디로 여행을 갈까요?!'
	@GetMapping("planSearch")
	public String planSearch() {
		return "plan/planSearch";
	}

	//페이지 이동 : plan만들기-지도(좌표)
	@GetMapping("planCreate/{loc}&{x}&{y}")
	public String planCreate(@PathVariable String loc, @PathVariable float x, @PathVariable float y, Model model) {
		model.addAttribute("loc", loc);
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "plan/planCreate";
	}
	
	//plan생성
	@PostMapping("planCreateOk")
	public ResponseEntity<String> planCreateOk(PlanVO vo, HttpSession session, HttpServletRequest req) {
		
		vo.setId((String)req.getSession().getAttribute("logId"));
		
		ResponseEntity<String> entity = null;	// 데이터와 처리상태를 가진다
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			String[] locations = req.getParameterValues("location");
			service.planCreate(vo);
			
			//정상 구현
			String msg = "<script>";
			msg += "alert('plan이 등록되었습니다.');";
			msg += "location.href='/mypage/myPlan';";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch(Exception e) {			//등록 실해 시
			e.printStackTrace();
			String msg = "<script>";
			msg += "alert('plan 등록에 실패하였습니다..');";
			msg += "location.href=history.back();";
			msg += "</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
	
	
}