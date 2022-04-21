package com.triptogether.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triptogether.service.LikesService;
import com.triptogether.vo.LikesVO;

@RestController
public class LikesController {
	@Autowired
	private LikesService service;
	
	// 좋아요
	@PostMapping("/likes/{no}")
	public int likes(@PathVariable int no, HttpSession session) {
		String logId = (String) session.getAttribute("logId");
		
		LikesVO likes = new LikesVO();
		
		likes.setBoard_no(no);
		likes.setId(logId);
		
		int result = service.likes(likes);
		if(result > 0) {
			session.setAttribute("isLikes", "L");
		}
		
		
		return service.likesCnt(likes);
	}
	
	// 좋아요 취소
	@PostMapping("/unlikes/{no}")
	public int unlikes(@PathVariable int no, HttpSession session) { 
		String logId = (String)session.getAttribute("logId"); 
		
		LikesVO likes = new LikesVO();
		
		likes.setBoard_no(no);
		likes.setId(logId);
		
		int result = service.unlikes(likes);
		if(result > 0) {
			session.setAttribute("isLikes", "U");
		}
		
		Integer likeCnt = service.likesCnt(likes);
		System.out.println(likeCnt);
		if(likeCnt == null) {
			return 0;
		}else {
			return likeCnt;
		}
	}
	

}
