package com.triptogether.controller;

//import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triptogether.service.FollowService;
import com.triptogether.service.UserService;
import com.triptogether.vo.FollowVO;
import com.triptogether.vo.UserVO;

@RestController
public class FollowController {
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private FollowService followService;
	@Autowired
	private UserService userService;
	
	//횈횊쨌횓쩔챙 
	@PostMapping("/follow/{id}")
	public String follow(@PathVariable String id, HttpSession session, Model model) throws Exception{
		//LOGGER.info("/follow"+id+"쨈횚�횉 횈횊쨌횓쩔챙 쩔채횄쨩");
		
		Object object = session.getAttribute("login");
		UserVO followingUser = (UserVO)object;
		UserVO followerUser = userService.inquiryOfUserById(id);
		
		FollowVO follow = new FollowVO();
		follow.setFollowingUser(followingUser.getUserNo());
		follow.setFollowerUser(followerUser.getUserNo());
		
		followService.follow(follow);
		
		return "FollowOk";
	}
	
	//쩐챨횈횊쨌횓쩔챙
	@PostMapping("/unfollow/{id}")
	public String unfollow(@PathVariable String id, HttpSession session, Model model) throws Exception{
		//LOGGER.info("/unfollow/"+id+"쨈횚�횉 쩐챨횈횊쨌횓쩔챙 쩔채횄쨩");
		
		Object object = session.getAttribute("login");
		UserVO followingUser = (UserVO)object;
		UserVO followerUser = userService.inquiryOfUserById(id);
		
		FollowVO follow = new FollowVO();
		follow.setFollowingUser(followingUser.getUserNo());
		follow.setFollowerUser(followerUser.getUserNo());
		
		followService.unfollow(follow);
		
		return "UnfollowOk";
	}
	
}
	