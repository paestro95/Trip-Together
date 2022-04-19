package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.ReplyVO;

@Mapper
@Repository
public interface ReplyDAO {
		//´ñ±Ûµî·Ï
		public int replyWrite(ReplyVO vo);
		
		//´ñ±Û¸ñ·Ï
		public List<ReplyVO> replyList(int board_no);
		
		//´ñ±Û¼öÁ¤
		public int replyEdit(ReplyVO vo);
		
		//´ñ±Û»èÁ¦
		public int replyDel(int reply_no, String id);
}
