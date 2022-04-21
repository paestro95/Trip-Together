package com.triptogether.service;

import java.util.List;

import com.triptogether.vo.CommunityVO;

public interface CommunityService {
	// Ŀ�´�Ƽ ���
	public List<CommunityVO> communityList(String id);

	// Ŀ�´�Ƽ ����Ʈ �űԼ����� �ҷ�����
	public List<CommunityVO> communityNewList(String id);
	
	// Ŀ�´�Ƽ �Խù� 1�� ����
	public CommunityVO communitySelect(int no);
	
	// Ŀ�´�Ƽ �۵��
	public int communityInsert(CommunityVO vo, String[] filePath);
	
	// Ŀ�´�Ƽ �ۼ���
	public int communityUpdate(CommunityVO vo, String[] filePath);
	
	// Ŀ�´�Ƽ �ۻ���
	public int communityDelete(int no, String id);	
	
	// user�� �α� �Խù� ��������(4��)
	public List<CommunityVO> hotPostSelect(String id);
	
	// user�� ��� �Խù� ��������
	public List<CommunityVO> userPostAllSelect(String id);
	
	//�ش�ID ������ ��ǥ������ �ҷ�����
	public List<CommunityVO> firstPhotoSelect(String id);
	
	// �̹��� �α� ������ ����
    public List<CommunityVO> bestPostSelect();

    // �̹��� ������ ����(3��)
    public List<CommunityVO> weeklyPostSelect();
}