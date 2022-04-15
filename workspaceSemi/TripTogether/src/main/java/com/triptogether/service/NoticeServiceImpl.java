package com.triptogether.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.triptogether.dao.NoticeDAO;
import com.triptogether.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Inject
    NoticeDAO dao;
    @Override
    public List<NoticeVO> allSelect() {
        return dao.allSelect();
    }
    
    @Override
    public NoticeVO noticeSelect(int no) {
        return dao.noticeSelect(no);
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
    public int noticeDelete(int no, String id) {
        return dao.noticeDelete(no, id);
    }

    

}