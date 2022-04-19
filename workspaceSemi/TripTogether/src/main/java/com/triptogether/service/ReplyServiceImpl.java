package com.triptogether.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptogether.dao.ReplyDAO;
import com.triptogether.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	ReplyDAO dao;
	
	@Override
	public int replyWrite(ReplyVO vo) {
		return dao.replyWrite(vo);
	}

	@Override
	public List<ReplyVO> replyList(int board_no) {
		return dao.replyList(board_no);
	}

	@Override
	public int replyEdit(ReplyVO vo) {
		return dao.replyEdit(vo);
	}

	@Override
	public int replyDel(int reply_no, String id) {
		return dao.replyDel(reply_no, id);
	}

}
