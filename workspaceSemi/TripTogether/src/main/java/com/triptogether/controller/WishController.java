package com.triptogether.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triptogether.service.UserService;
import com.triptogether.service.WishService;
import com.triptogether.vo.WishVO;

@RestController
public class WishController {
	@Autowired
	private UserService userService;
	@Autowired
	private WishService wishService;
	
	@PostMapping("/wish")
	public int wish(int board_no, HttpSession session) {
		System.out.println("board_no: "+board_no);
		Object object = session.getAttribute("logId");
		String logId = (String) object;
		
		WishVO wish = new WishVO();
		
		wish.setBoard_no(board_no);
		wish.setId(logId);
		//wish.setWish_no(wish_no);
		
		int res=wishService.wish(wish);
		if(res>0) {
			session.setAttribute("isWish", "W");
		}
		
		return wishService.wishCnt(wish);
	}
	
	//위시 취소
	@PostMapping("/unwish")
	public int unwish(int board_no, HttpSession session) {
		Object object = session.getAttribute("logId");
		String logId = (String) object;
		
		WishVO wish = new WishVO();
		
		wish.setBoard_no(board_no);
		wish.setId(logId);
		
		int res=wishService.unwish(wish);
		if(res>0) {
			session.setAttribute("isWish", "N");
		}
		
		Integer wishCnt = wishService.wishCnt(wish);
		System.out.println(wishCnt);
		if(wishCnt==null) {
			return 0;
		}else {
			return wishCnt;
		}
	}
	
	//위시 확인
	
	 @PostMapping("/oxwish") 
	 public int oxwish(int board_no, HttpSession session, WishVO wish) {
		 int wcnt = wishService.oxWish(wish); 
		 System.out.println(wcnt);
		 return wcnt; 
	}	 
}
