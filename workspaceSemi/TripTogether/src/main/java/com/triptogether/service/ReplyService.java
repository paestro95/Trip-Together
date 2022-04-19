package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.ReplyVO;

public interface ReplyService {
	//´ñ±Ûµî·Ï
	public int replyWrite(ReplyVO vo);
			
	//´ñ±Û¸ñ·Ï
	public List<ReplyVO> replyList(int board_no);
			
	//´ñ±Û¼öÁ¤
	public int replyEdit(ReplyVO vo);
			
	//´ñ±Û»èÁ¦
	public int replyDel(int reply_no, String id);
}
