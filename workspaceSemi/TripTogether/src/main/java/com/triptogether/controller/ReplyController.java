package com.triptogether.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.triptogether.service.ReplyService;
import com.triptogether.vo.ReplyVO;

@RestController
public class ReplyController {
	@Inject
	ReplyService service;
	
	//´ñ±Ûµî·Ï
	@RequestMapping(value="/reply/replyWriteOK", method=RequestMethod.POST)
	public int writeOk(ReplyVO vo, HttpSession session) {
		vo.setId((String)session.getAttribute("logId"));	
		return service.replyWrite(vo);
	}
	
	//´ñ±Û¸ñ·Ï
	@RequestMapping("/reply/replyList")
	public List<ReplyVO> list(int board_no) {
		return service.replyList(board_no);
	}
	
	//´ñ±Û¼öÁ¤
	@PostMapping("/reply/replyEditOk")
	public int editOk(ReplyVO vo, HttpSession session) {
		vo.setId((String)session.getAttribute("logId"));
		return service.replyEdit(vo);
	}
	
	//´ñ±Û»èÁ¦
	@GetMapping("/reply/replyDel")
	public int delOk(int reply_no, HttpSession session) {
		return service.replyDel(reply_no, (String)session.getAttribute("logId"));
	}
}
