package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.ReplyVO;

@Mapper
@Repository
public interface ReplyDAO {
		//��۵��
		public int replyWrite(ReplyVO vo);
		
		//��۸��
		public List<ReplyVO> replyList(int board_no);
		
		//��ۼ���
		public int replyEdit(ReplyVO vo);
		
		//��ۻ���
		public int replyDel(int reply_no, String id);
}
