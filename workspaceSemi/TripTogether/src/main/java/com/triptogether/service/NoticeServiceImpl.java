package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.NoticeDAO;
import com.triptogether.vo.NoticeVO;
import com.triptogether.vo.PagingVO;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Inject
    NoticeDAO dao;

	@Override
	public List<NoticeVO> allSelect(PagingVO pVO) {
		return dao.allSelect(pVO);
	}

	@Override
	public NoticeVO noticeSelect(int notice_no) {
		return dao.noticeSelect(notice_no);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		return dao.noticeInsert(vo);
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		return dao.noticeUpdate(vo);
	}

	@Override
	public int noticeDelete(int notice_no, String id) {
		return dao.noticeDelete(notice_no, id);
	}

	@Override
	public int totalRecord(PagingVO pVO) {
		return dao.totalRecord(pVO);
	}

	@Override
	public NoticeVO latestNoticeSelect() {
		return dao.latestNoticeSelect();
	}
  
}