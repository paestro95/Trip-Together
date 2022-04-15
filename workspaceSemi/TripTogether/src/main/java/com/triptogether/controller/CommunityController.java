package com.triptogether.controller;

import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.CommunityService;
import com.triptogether.vo.CommunityVO;

@RestController
public class CommunityController {
	@Inject
	CommunityService service;

	// 커뮤니티 리스트 페이지로 이동
		@GetMapping("/community/communityList")
		public ModelAndView communityList() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/communityList");
			return mav;
		}

	// 커뮤니티뷰 communityView --> 수정해야함
	@RequestMapping("/community/communityView")
	public ModelAndView communityView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("community/communityView");
		return mav;
	}

	// 커뮤니티 글작성 페이지로 이동
		@GetMapping("/community/communityWrite")
		public ModelAndView CommunityWrite() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/communityWrite");
			return mav;
		}

	@PostMapping("/community/communityWriteOk")
	public ResponseEntity<String> communityWriteOk(CommunityVO vo, HttpServletRequest request) {
		vo.setId((String) request.getSession().getAttribute("logId"));

		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));

		try {
			// 커뮤니티 등록 성공시 커뮤니티 리스트로 이동
			service.communityInsert(vo);
			String msg = "<script>alert('글이 등록되었습니다.');location.href='/community/communityList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// 공지사항 등록 실패시 글등록 폼으로 이동
			String msg = "<script>alert('글등록이 실패하였습니다.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 커뮤니티 글수정 페이지로 이동
		@GetMapping("/community/communityUpdate")
		public ModelAndView CommunityUpdate() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/communityUpdate");
			return mav;
		}

	@PostMapping("/community/communityUpdateOk")
	public ResponseEntity<String> communityUpdateOk(CommunityVO vo, HttpSession session) {
		vo.setId((String) session.getAttribute("logId"));

		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		try {
			service.communityUpdate(vo);

			String msg = "<script>alert('글이 수정되었습니다.'); location.href='/community/communityView?no'" + vo.getNo()
					+ "';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK); // 성공시 수정완료게시물로 이동
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('글수정 실패하였습니다.');history.go(-1);</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); // 실패시 다시 수정폼으로 -1
		}
		return entity;
	}
	// 위치 선택 지도로 이동
		@GetMapping("/community/locationMap")
		public ModelAndView locationMap() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/locationMap");
			return mav;
		}
		
	//커뮤니티 게시물 삭제
	@GetMapping("/community/communityDel")
	public ModelAndView communityDel(int no, HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		int result = service.communityDelete(no, id);
		if(result>0) {
			mav.setViewName("redirect:communityList");	//게시물 삭제 성공시 공지사항 리스트로
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:communityView");	//게시물 삭제 실패시 해당 게시물로
		}
		return mav;
	}	
	/*
	 * @RequestMapping(value = "main/post/{id}") public String
	 * post(@PathVariable("id") int id, Model model) throws Exception { String
	 * userId = SecurityContextHolder.getContext().getAuthentication().getName();
	 * User user = userService.findByUserId(userId);
	 * 
	 * model.addAttribute("postuserid", postService.findById(id).getUser().getId());
	 * model.addAttribute("p", postService.findById(id)); model.addAttribute("img",
	 * piService.findBypostId(id));
	 * 
	 * return "main/post"; }
	 */
}