package com.triptogether;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.CommunityService;

@RestController
public class HomeController {

	@Inject
    CommunityService cService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/home");
        return mav;
    }

    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("bestPostVO", cService.bestPostSelect());
        mav.addObject("weeklyPostVO", cService.weeklyPostSelect());
        mav.setViewName("/main/main");
        return mav;
    }

}
