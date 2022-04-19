package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.NoticeVO;
import com.triptogether.vo.PagingVO;

@Mapper
@Repository
public interface NoticeDAO {
    public List<NoticeVO> allSelect(PagingVO pVO);
    public NoticeVO noticeSelect(int notice_no);
    public int noticeInsert(NoticeVO vo);
    public int noticeUpdate(NoticeVO vo);
    public int noticeDelete(int notice_no, String id);
    public int totalRecord(PagingVO pVO);
    
    // ���� �ֱٿ� ���ε�� �������� ����
    public NoticeVO latestNoticeSelect();
}