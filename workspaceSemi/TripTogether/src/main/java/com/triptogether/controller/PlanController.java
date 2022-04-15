package com.triptogether.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan/")
public class PlanController {
	@GetMapping("planSearch")
	public String planSearch() {
		return "plan/planSearch";
	}

	@GetMapping("planCreate/{loc}&{x}&{y}")
	public String planCreate(@PathVariable String loc, @PathVariable float x, @PathVariable float y, Model model) {
		model.addAttribute("loc", loc);
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "plan/planCreate";
	}
}