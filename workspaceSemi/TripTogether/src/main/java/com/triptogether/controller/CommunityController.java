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

	// Ŀ�´�Ƽ ����Ʈ �������� �̵�
		@GetMapping("/community/communityList")
		public ModelAndView communityList() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/communityList");
			return mav;
		}

	// Ŀ�´�Ƽ�� communityView --> �����ؾ���
	@RequestMapping("/community/communityView")
	public ModelAndView communityView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("community/communityView");
		return mav;
	}

	// Ŀ�´�Ƽ ���ۼ� �������� �̵�
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
			// Ŀ�´�Ƽ ��� ������ Ŀ�´�Ƽ ����Ʈ�� �̵�
			service.communityInsert(vo);
			String msg = "<script>alert('���� ��ϵǾ����ϴ�.');location.href='/community/communityList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			// �������� ��� ���н� �۵�� ������ �̵�
			String msg = "<script>alert('�۵���� �����Ͽ����ϴ�.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// Ŀ�´�Ƽ �ۼ��� �������� �̵�
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

			String msg = "<script>alert('���� �����Ǿ����ϴ�.'); location.href='/community/communityView?no'" + vo.getNo()
					+ "';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK); // ������ �����Ϸ�Խù��� �̵�
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('�ۼ��� �����Ͽ����ϴ�.');history.go(-1);</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); // ���н� �ٽ� ���������� -1
		}
		return entity;
	}
	// ��ġ ���� ������ �̵�
		@GetMapping("/community/locationMap")
		public ModelAndView locationMap() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("community/locationMap");
			return mav;
		}
		
	//Ŀ�´�Ƽ �Խù� ����
	@GetMapping("/community/communityDel")
	public ModelAndView communityDel(int no, HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		int result = service.communityDelete(no, id);
		if(result>0) {
			mav.setViewName("redirect:communityList");	//�Խù� ���� ������ �������� ����Ʈ��
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:communityView");	//�Խù� ���� ���н� �ش� �Խù���
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