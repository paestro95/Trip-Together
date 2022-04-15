package com.triptogether.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.UserService;
import com.triptogether.vo.UserVO;


@Controller
@RequestMapping("/users/")
public class UserController {
   @Value("C:\\WebSemiProject\\workspaceSemi\\TripTogether\\src\\main\\webapp\\css\\")
   private String fileRealPath;
   
   @Inject
   UserService service;
   
   //페이지 이동 (임시) : 로그인 폼
   @GetMapping("login")
   public String login() {
      return "users/login";
   }
   
   //페이지 이동 (임시) : 회원가입 폼
   @GetMapping("joinForm")
   public String joinForm() {
      return "users/joinForm";
   }
   
   //페이지 이동 (임시) : 회원정보수정 폼
   @GetMapping("userEdit")
   public ModelAndView userEdit(HttpSession session) {
      String id = (String) session.getAttribute("logId");
      
      UserVO vo = service.userSelect(id);
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("vo", vo);
      
      mav.setViewName("users/userEdit");
      return mav;
   }
   
   //페이지 이동 (임시) : 타 회원 프로필 userView
   @GetMapping("userView")
   public String userView() {
      return "users/userView";
   }
   
   
   //회원등록
   @PostMapping("joinOk")
   public String joinOk(UserVO vo, Model model, @RequestParam("profileImg") MultipartFile profileImg) {
      int cnt = service.userInsert(vo);
      
      
      System.out.println(profileImg.getOriginalFilename());
      System.out.println(profileImg.getContentType());
      System.out.println(profileImg.getSize());
      System.out.println(profileImg.getName());
      
      Path filePath = Paths.get(fileRealPath+profileImg.getName()+vo.getId());
      try {
         Files.write(filePath, profileImg.getBytes());
         System.out.println(filePath+"경로에 이미지가 저장됨");
         //getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      model.addAttribute("cnt", cnt);
      
      
      return "users/joinResult";
   }
   
   // 아이디 중복검사
   @PostMapping("userIdCheck")
   @ResponseBody
   public int idCheck(String id) {
      // id가 DB에 존재하는지 확인
      int cnt = service.idCheck(id);
      return cnt;
   }
   
   //회원정보 수정
   @PostMapping("userEditOk")
   public ModelAndView userEditOk(UserVO vo, HttpSession session) {
      vo.setId((String) session.getAttribute("logId"));
      
      service.userUpdate(vo);
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("redirect:userEdit");
      return mav;
   }
   
   
   //로그인 (임시)
   @PostMapping("loginOk")
   public ResponseEntity<String> loginOk(UserVO vo, HttpSession session) {
      ResponseEntity<String> entity = null;
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "text/html; charset=utf-8");

      try {
         UserVO rVo = service.loginCheck(vo);
         if (rVo != null) { // 로그인 성공
            session.setAttribute("logId", rVo.getId());
            session.setAttribute("logName", rVo.getName());
            session.setAttribute("logStatus", "Y");
            String msg = "<script> location.href='/';</script>";
            entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
         } else {
            throw new Exception();
         }

      } catch (Exception e) {
         e.printStackTrace();
         // 로그인 실패
         String msg = "<script> alert('로그인 실패'); history.back(); </script>";
         entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
      }

      return entity;
   }
}