package com.triptogether.controller;

import java.util.List;

//import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.FollowService;
import com.triptogether.service.UserService;
import com.triptogether.vo.FollowVO;
import com.triptogether.vo.UserVO;

@RestController
public class FollowController {

	@Autowired
	private FollowService followService;
	@Autowired
	private UserService userService;

	// 팔로우
	@PostMapping("/follow/{id}")

	public String follow(@PathVariable String id, HttpSession session) {
		System.out.println(id);
		Object object = session.getAttribute("logId");
		String logId = (String) object;
		System.out.println(logId+"<<<<");
	
		FollowVO follow = new FollowVO();
		
		follow.setFollow_id(id);
		follow.setId(logId);

		int n = followService.follow(follow);
		if(n>0) {
			session.setAttribute("isFollow", "F");
		}

		return "FollowOk";
	}

	// 언팔로우
	@PostMapping("/unfollow/{id}")
	public String unfollow(@PathVariable String id, HttpSession session) {
		System.out.println(id);
		Object object = session.getAttribute("logId");
		String logId = (String) object;

		FollowVO follow = new FollowVO();
		
		follow.setFollow_id(id);
		follow.setId(logId);

		int n = followService.unfollow(follow);
		if(n>0) {
			session.setAttribute("isFollow", "U");
		}

		return "UnfollowOk";
	}


	//팔로우-팔로잉 관계 확인
	@PostMapping("/oxfollow/{id}") 
	public int oxfollow(@PathVariable String id, HttpSession session, FollowVO follow) {
		
		int cnt = followService.oxFollow(follow);
		System.out.println(cnt);
		return cnt;
	}	
	
	//팔로우 리스트 조회		220420 오어진 수정-->
	@GetMapping("/follow/selectFollowingList")
	public ModelAndView selectFollowingList(HttpSession session) {
		
		String id = (String)session.getAttribute("logId");
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("fList", followService.selectFollowingList(id));
		mav.addObject("f_ingCnt", followService.followingCnt(id));
		mav.addObject("f_erCnt", followService.followerCnt(id));
		mav.setViewName("mypage/myFollowing");		//사용처에 따라 링크는 바꿔야 될.. 까요?
		return mav;
	}
	
	//팔로워 리스트 조회
}
