package com.triptogether.controller;

//import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

		followService.follow(follow);

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

		followService.unfollow(follow);

		return "UnfollowOk";
	}


	//팔로우 유무 조회	
	@PostMapping("/oxfollow/{id}") 
	public int oxfollow(@PathVariable String id, HttpSession session, FollowVO follow) {
		
		int cnt = followService.oxFollow(follow);
		System.out.println(cnt);
		return cnt;
	}	
	
	//팔로우 리스트 조회
	
	//팔로워 리스트 조회
}
