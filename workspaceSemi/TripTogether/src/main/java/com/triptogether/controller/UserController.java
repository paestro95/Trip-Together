package com.triptogether.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triptogether.service.CommunityService;
import com.triptogether.service.PlanService;
import com.triptogether.service.UserService;
import com.triptogether.vo.KakaoProfile;
import com.triptogether.vo.OAuthToken;
import com.triptogether.vo.PlanVO;
import com.triptogether.vo.UserVO;


@Controller
@RequestMapping("/users/")
public class UserController {
   @Value("C:\\WebSemiProject\\workspaceSemi\\TripTogether\\src\\main\\webapp\\profiles\\")
   private String fileRealPath;
   
   @Inject
   UserService service;
   
   @Inject
   PlanService p_service;
   
   @Inject
   CommunityService cService;
   
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
   
   //������ �̵� (�ӽ�) : ȸ�� ������ ���� userView
   @GetMapping("userView")
   public ModelAndView userView(String id) {
	   ModelAndView mav = new ModelAndView();
	   
	   //DBó��
	   //mav.addObject("uView", service.userView(id));
	   //mav.addObject("list", ?service.bestList(id));
	   //mav.addObject("list", p_service.planList(id));
	   
	   /* 220419 ������ �߰�+���� */
	   mav.addObject("vo", service.userSelect(id));
	   mav.addObject("hList", cService.hotPostSelect(id));
	   mav.addObject("pList", cService.userPostAllSelect(id));
	   mav.addObject("pSize", cService.userPostAllSelect(id).size());
	   
	   mav.setViewName("users/userView");
      return mav;
   }
   
   
   //ȸ�����
   @PostMapping("joinOk")
   public String joinOk(UserVO vo, Model model, @RequestParam("profileImg") MultipartFile profileImg) {
      
      Path filePath = Paths.get(fileRealPath+profileImg.getName()+vo.getId());
      try {
         Files.write(filePath, profileImg.getBytes());
         System.out.println(filePath+"��ο� �̹����� �����");
         //getBytes�� �̹��� ��ü�� �̰� ���Ϸ� ��ȯ�ؼ� �����ؾ��Ѵ�.
      } catch (Exception e) {
         e.printStackTrace();
      }
      String pathName = "\\profiles\\profileImg"+vo.getId();
      int cnt = service.userInsert(vo, pathName);
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
   public ModelAndView userEditOk(UserVO vo, HttpSession session, @RequestParam("profileImg") MultipartFile profileImg) {
      vo.setId((String) session.getAttribute("logId"));
      
      Path filePath = Paths.get(fileRealPath+profileImg.getName()+vo.getId());
      try {
         Files.write(filePath, profileImg.getBytes());
         System.out.println(filePath+"��ο� �̹����� �����");
         //getBytes�� �̹��� ��ü�� �̰� ���Ϸ� ��ȯ�ؼ� �����ؾ��Ѵ�.
      } catch (Exception e) {
         e.printStackTrace();
      }
      String pathName = "\\profiles\\profileImg"+vo.getId();
      service.userUpdate(vo, pathName);
      
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
            session.setAttribute("logImg", rVo.getUser_img());
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
   
   //�α׾ƿ�
   @GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); // ���� ���� = �α׾ƿ� / ���ο� ������ �Ҵ�ȴ�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
   
   
// ********************* īī�� �α��� (������) *************************************** // 
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) { // Data�� �������ִ� ��Ʈ�ѷ� �Լ�
		// POST ������� key=value �����͸� ��û (īī��������)
		// Retrofit2 (�ȵ���̵忡�� ���)
		// Okhttp
		// RestTemplate
		RestTemplate rt = new RestTemplate();

		// HttpHeader ������Ʈ ����
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody ������Ʈ ����
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "66837c1b56e8c94b59ea0bf0ec332abe");
		params.add("redirect_uri", "http://localhost:9060/users/auth/kakao/callback");
		params.add("code", code);


		// HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = rt.exchange(""
				+ "https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				kakaoTokenRequest, 
				String.class
		);
		
		
		// ��ū�޼ҵ�, ����������޴� �޼ҵ� 
		
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

// 		System.out.println("īī�� ������ ��ū:" + oauthToken.getAccess_token());
//		return response.getBody();
		 

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader ������Ʈ ����
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader�� HttpBody�� �ϳ��� ������Ʈ�� ���
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http ��û�ϱ� - POST������� - �׸��� response ������ �������
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST,
				kakaoProfileRequest2, 
				String.class);
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();		
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// User ������Ʈ : username, password, email..?
		
		System.out.println("īī�� ���̵�(��ȣ):"+kakaoProfile.id);
		System.out.println("īī�� �̸���:"+kakaoProfile.kakao_account.email);
		System.out.println("īī�� �г���:"+kakaoProfile.properties.nickname);
		
		// �߰����� ȸ�������� �ʿ��ϸ� ��������� ����â�� ���� �Է��� �޾ƾ���. 
		
		System.out.println("triptogether���� ��������:"+kakaoProfile.kakao_account.email+"_"+kakaoProfile.id);
		System.out.println("triptogether���� �̸���:"+kakaoProfile.kakao_account.email);
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("triptogether���� �н�����"+garbagePassword);
		
		
		return response2.getBody();
	} 
   
   
}