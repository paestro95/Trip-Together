package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.ReplyVO;

public interface ReplyService {
	//��۵��
	public int replyWrite(ReplyVO vo);
			
	//��۸��
	public List<ReplyVO> replyList(int board_no);
			
	//��ۼ���
	public int replyEdit(ReplyVO vo);
			
	//��ۻ���
	public int replyDel(int reply_no, String id);
}
