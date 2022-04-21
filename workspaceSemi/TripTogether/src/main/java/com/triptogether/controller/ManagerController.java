package com.triptogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager/")
public class ManagerController {

	@GetMapping("managerMember")
	public ModelAndView managerMember() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("manager/managerMember");
		return mav;
	}
	
}
