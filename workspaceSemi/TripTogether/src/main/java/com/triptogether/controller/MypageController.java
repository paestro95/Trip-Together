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

import com.triptogether.service.CommunityService;
import com.triptogether.service.FollowService;
import com.triptogether.service.PlanService;
import com.triptogether.service.WishService;
import com.triptogether.vo.CommunityVO;
import com.triptogether.vo.PlanVO;
import com.triptogether.vo.WishVO;

@Controller
@RequestMapping("/mypage/")
public class MypageController {
	
	@Inject
	CommunityService c_service;
	
	@Inject
	PlanService p_service;
	
	@Inject
	FollowService followService;
	
	@Inject
	WishService	wishService;

	// ������ �̵� (�ӽ�) : �α��� ��
	@GetMapping("myPlan")
	public ModelAndView myPlan(HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		List<PlanVO> list = p_service.planList(id);
		mav.addObject("list", list);
		mav.addObject("f_ingCnt", followService.followingCnt(id));
		mav.addObject("f_erCnt", followService.followerCnt(id));
		mav.setViewName("mypage/myPlan");
		return mav;
	}
/*
	// ������ �̵� : PLACE �޴�
	@GetMapping("myPlace")
	public String myPlace() {
		return "mypage/myPlace";
	}*/

	// ������ �̵� : WISH LIST �޴�
	@GetMapping("myWishList")
	public ModelAndView myWishList(HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("f_ingCnt", followService.followingCnt(id));
		mav.addObject("f_erCnt", followService.followerCnt(id));
		mav.addObject("wList", wishService.selectWishList(id));
		
		//mav.addObject("wbList", c_service.communitySelect(wl.get(0).getBoard_no()));
		mav.setViewName("mypage/myWishList");
		return mav;
	}

	// ������ �̵� : FOLLOWING �޴�
	@GetMapping("myFollowing")
	public ModelAndView myFollowing(HttpSession session) {
		//return "mypage/myFollowing";
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("f_ingCnt", followService.followingCnt(id));
		mav.addObject("f_erCnt", followService.followerCnt(id));
		mav.addObject("fList", followService.selectFollowingList(id));
		mav.setViewName("mypage/myFollowing");		//���ó�� ���� ��ũ�� �ٲ�� ��.. ���?
		return mav;
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
