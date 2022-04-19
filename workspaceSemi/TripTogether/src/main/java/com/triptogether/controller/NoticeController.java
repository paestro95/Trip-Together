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
	
	//공지사항 메인페이지, 리스트
	@GetMapping("/notice/noticeList")
	public ModelAndView allSelect(PagingVO pVO) {	//공지사랑 리스트, 주소설정
		ModelAndView mav = new ModelAndView();
		
		pVO.setTotalRecord(service.totalRecord(pVO));
		
		mav.addObject("lst", service.allSelect(pVO));	//allSelect()사용
		mav.addObject("pVO", pVO);
		mav.setViewName("notice/noticeList");
		return mav;
	}
	
	//공지사항 게시물 보기
	@RequestMapping("/notice/noticeView")
	public ModelAndView noticeView(@RequestParam("no") int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.noticeSelect(no));
		mav.setViewName("notice/noticeView");
		return mav;
	}
	
	//공지사항 게시물 작성
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
			//공지사항 등록 성공시 공지사항 리스트로 이동
			service.noticeInsert(vo);	//noticeInsert() 사용
			String msg = "<script>alert('글이 등록되었습니다.');location.href='/notice/noticeList';</script>"; 
			entity = new ResponseEntity<String>(msg, headers,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			//공지사항 등록 실패시 글등록 폼으로 이동
			String msg = "<script>alert('글등록이 실패하였습니다.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	//공지사항 게시물 수정
	@GetMapping("/notice/noticeUpdate")
	public ModelAndView noticeUpdate(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", service.noticeSelect(no));	//noticeSelect() 사용
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
			service.noticeUpdate(vo);	//noticeUpdate() 사용
			int result = service.noticeUpdate(vo);
			if(result>0) {
			String msg = "<script>alert('글이 수정되었습니다.'); location.href='/notice/noticeView?no="+vo.getNotice_no()+"';</script>";
			entity = new ResponseEntity<String>(msg,headers,HttpStatus.OK);	}//성공시 수정완료게시물로 이동
			else {
				throw new Exception();
			}
		}catch(Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('글수정 실패하였습니다.');history.go(-1);</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); //실패시 다시 수정폼으로 -1
		}
		return entity;
	}
	
	//공지사항 게시물 삭제
	@GetMapping("/notice/noticeDel")
	public ModelAndView noticeDel(int no, HttpSession session) {
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		int result = service.noticeDelete(no, id);
		if(result>0) {
			mav.setViewName("redirect:noticeList");	//게시물 삭제 성공시 공지사항 리스트로
		}else {
			mav.addObject("no", no);
			mav.setViewName("redirect:noticeView");	//게시물 삭제 실패시 해당 게시물로
		}
		return mav;
	}


	
}