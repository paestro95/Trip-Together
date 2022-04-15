package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.NoticeVO;

public interface NoticeService {
    public List<NoticeVO> allSelect();
    public NoticeVO noticeSelect(int no);
    public int noticeInsert(NoticeVO vo);
    public int noticeUpdate(NoticeVO vo);
    public int noticeDelete(int no, String id);
}