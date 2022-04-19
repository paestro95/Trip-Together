package com.triptogether.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.PlanService;
import com.triptogether.vo.PlanVO;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	
	@Inject
	PlanService p_service;

	// ������ �̵� (�ӽ�) : �α��� ��
	@GetMapping("myPlan")
	public ModelAndView myPlan(HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		List<PlanVO> list = p_service.planList(id);
		mav.addObject("list", list);
		mav.setViewName("mypage/myPlan");
		return mav;
	}

	// ������ �̵� : PLACE �޴�
	@GetMapping("myPlace")
	public String myPlace() {
		return "mypage/myPlace";
	}

	// ������ �̵� : WISH LIST �޴�
	@GetMapping("myWishList")
	public String myWishList() {
		return "mypage/myWishList";
	}

	// ������ �̵� : FOLLOWING �޴�
	@GetMapping("myFollowing")
	public String myFollowing() {
		return "mypage/myFollowing";
	}
	
	//plan����
	@GetMapping("planDel")
	public ResponseEntity<String> planDel(int no, HttpSession session) {
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		
		String id = (String) session.getAttribute("logId");
	////////////////////////////////////////////////////////////////////////////////////
		int result = p_service.planDelete(no, id);
		try {
			if (result > 0) { // �Խù� ���� ������ �������� ����Ʈ��
				String msg = "<script>alert('plan ������ �Ϸ�Ǿ����ϴ�.'); location.href='/mypage/myPlan';</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
			} else { 
				throw new Exception();
			}
		}catch(Exception e) {
			e.printStackTrace();
			// �Խù� ���� ���н� �ش� �Խù���
			String msg = "<script>alert('plan ������ �����Ͽ����ϴ�.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
