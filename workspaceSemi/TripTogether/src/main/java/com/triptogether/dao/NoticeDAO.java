package com.triptogether.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.triptogether.vo.NoticeVO;

@Mapper
@Repository
public interface NoticeDAO {
    public List<NoticeVO> allSelect();
    public NoticeVO noticeSelect(int no);
    public int noticeInsert(NoticeVO vo);
    public int noticeUpdate(NoticeVO vo);
    public int noticeDelete(int no, String id);
}