package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.NoticeVO;
import com.triptogether.vo.PagingVO;

public interface NoticeService {
    public List<NoticeVO> allSelect(PagingVO pVO);
    public NoticeVO noticeSelect(int notice_no);
    public int noticeInsert(NoticeVO vo);
    public int noticeUpdate(NoticeVO vo);
    public int noticeDelete(int notice_no, String id);
    public int totalRecord(PagingVO pVO);
    
    // ���� �ֱٿ� ���ε�� �������� ����
    public NoticeVO latestNoticeSelect();
}