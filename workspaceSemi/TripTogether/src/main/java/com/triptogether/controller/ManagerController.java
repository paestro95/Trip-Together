package com.triptogether.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.UserService;
import com.triptogether.vo.UserVO;

@Controller
@RequestMapping("/manager/")
public class ManagerController {
	@Inject
	UserService uService;

	@GetMapping("managerMember")
	public ModelAndView managerMember() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("memberTotalNo", uService.userAllSelect().size());
		mav.addObject("list", uService.userAllSelect());
		mav.setViewName("manager/managerMember");
		return mav;
	}

	@PostMapping("memberDelOK")
	public ModelAndView memberDelOK(UserVO vo) {
		ModelAndView mav = new ModelAndView();

		uService.userListMultiDelete(vo);
		mav.setViewName("redirect:managerMember");

		return mav;

	}
}