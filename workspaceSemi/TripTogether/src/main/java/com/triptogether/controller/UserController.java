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
   
   //������ �̵� (�ӽ�) : �α��� ��
   @GetMapping("login")
   public String login() {
      return "users/login";
   }
   
   //������ �̵� (�ӽ�) : ȸ������ ��
   @GetMapping("joinForm")
   public String joinForm() {
      return "users/joinForm";
   }
   
   //������ �̵� (�ӽ�) : ȸ���������� ��
   @GetMapping("userEdit")
   public ModelAndView userEdit(HttpSession session) {
      String id = (String) session.getAttribute("logId");
      
      UserVO vo = service.userSelect(id);
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("vo", vo);
      
      mav.setViewName("users/userEdit");
      return mav;
   }
   
   //������ �̵� (�ӽ�) : Ÿ ȸ�� ������ userView
   @GetMapping("userView")
   public String userView() {
      return "users/userView";
   }
   
   
   //ȸ�����
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
         System.out.println(filePath+"��ο� �̹����� �����");
         //getBytes�� �̹��� ��ü�� �̰� ���Ϸ� ��ȯ�ؼ� �����ؾ��Ѵ�.
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      model.addAttribute("cnt", cnt);
      
      
      return "users/joinResult";
   }
   
   // ���̵� �ߺ��˻�
   @PostMapping("userIdCheck")
   @ResponseBody
   public int idCheck(String id) {
      // id�� DB�� �����ϴ��� Ȯ��
      int cnt = service.idCheck(id);
      return cnt;
   }
   
   //ȸ������ ����
   @PostMapping("userEditOk")
   public ModelAndView userEditOk(UserVO vo, HttpSession session) {
      vo.setId((String) session.getAttribute("logId"));
      
      service.userUpdate(vo);
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("redirect:userEdit");
      return mav;
   }
   
   
   //�α��� (�ӽ�)
   @PostMapping("loginOk")
   public ResponseEntity<String> loginOk(UserVO vo, HttpSession session) {
      ResponseEntity<String> entity = null;
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "text/html; charset=utf-8");

      try {
         UserVO rVo = service.loginCheck(vo);
         if (rVo != null) { // �α��� ����
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
         // �α��� ����
         String msg = "<script> alert('�α��� ����'); history.back(); </script>";
         entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
      }

      return entity;
   }
}