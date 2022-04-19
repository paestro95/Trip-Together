package com.triptogether.controller;

import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.NoticeService;
import com.triptogether.vo.NoticeVO;
import com.triptogether.vo.PagingVO;


@RestController
public class NoticeController {
	@Inject
	NoticeService service;
	
	//�������� ����������, ����Ʈ
	@GetMapping("/notice/noticeList")
	public ModelAndView allSelect(PagingVO pVO) {	//������� ����Ʈ, �ּҼ���
		ModelAndView mav = new ModelAndView();
		
		pVO.setTotalRecord(service.totalRecord(pVO));
		
		mav.addObject("lst", service.allSelect(pVO));	//allSelect()���
		mav.addObject("pVO", pVO);
		mav.setViewName("notice/noticeList");
		return mav;
	}
	
	//�������� �Խù� ����
	@RequestMapping("/notice/noticeView")
	public ModelAndView noticeView(@RequestParam("no") int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.noticeSelect(no));
		mav.setViewName("notice/noticeView");
		return mav;
	}
	
	//�������� �Խù� �ۼ�
	@GetMapping("/notice/noticeWrite")
	public ModelAndView noticeWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/noticeWrite");
		return mav;
	}
	@ExceptionHandler
	@PostMapping("/notice/noticeWriteOk")
	public ResponseEntity<String> noticeWriteOk(NoticeVO vo, HttpServletRequest request){		
		vo.setId((String)request.getSession().getAttribute("logId"));
		
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		
		try {
			//�������� ��� ������ �������� ����Ʈ�� �̵�
			service.noticeInsert(vo);	//noticeInsert() ���
			String msg = "<script>alert('���� ��ϵǾ����ϴ�.');location.href='/notice/noticeList';</script>"; 
			entity = new ResponseEntity<String>(msg, headers,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			//�������� ��� ���н� �۵�� ������ �̵�
			String msg = "<script>alert('�۵���� �����Ͽ����ϴ�.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	//�������� �Խù� ����
	@GetMapping("/notice/noticeUpdate")
	public ModelAndView noticeUpdate(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.noticeSelect(no));	//noticeSelect() ���
		mav.setViewName("notice/noticeUpdate");
		return mav;
	}
	@PostMapping("/notice/noticeUpdateOk")
	public ResponseEntity<String> noticeUpdateOk(NoticeVO vo, HttpSession session){
		vo.setId((String)session.getAttribute("logId"));
		
		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		try {
			service.noticeUpdate(vo);	//noticeUpdate() ���
			int result = service.noticeUpdate(vo);
			if(result>0) {
			String msg = "<script>alert('���� �����Ǿ����ϴ�.'); location.href='/notice/noticeView?no="+vo.getNotice_no()+"';</script>";
			entity = new ResponseEntity<String>(msg,headers,HttpStatus.OK);	}//������ �����Ϸ�Խù��� �̵�
			else {
				throw new Exception();
			}
		}catch(Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('�ۼ��� �����Ͽ����ϴ�.');history.go(-1);</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); //���н� �ٽ� ���������� -1
		}
		return entity;
	}
	
	//�������� �Խù� ����
	@GetMapping("/notice/noticeDel")
	public ModelAndView noticeDel(int no, HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		int result = service.noticeDelete(no, id);
		if(result>0) {
			mav.setViewName("redirect:noticeList");	//�Խù� ���� ������ �������� ����Ʈ��
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:noticeView");	//�Խù� ���� ���н� �ش� �Խù���
		}
		return mav;
	}


	
}