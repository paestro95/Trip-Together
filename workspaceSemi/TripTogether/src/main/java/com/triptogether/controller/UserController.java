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
   
   //페이지 이동 (임시) : 회원 프로필 정보 userView
   @GetMapping("userView")
   public ModelAndView userView(String id) {
	   ModelAndView mav = new ModelAndView();
	   
	   //DB처리
	   //mav.addObject("uView", service.userView(id));
	   //mav.addObject("list", ?service.bestList(id));
	   //mav.addObject("list", p_service.planList(id));
	   
	   /* 220419 수진님 추가+수정 */
	   mav.addObject("vo", service.userSelect(id));
	   mav.addObject("hList", cService.hotPostSelect(id));
	   mav.addObject("pList", cService.userPostAllSelect(id));
	   mav.addObject("pSize", cService.userPostAllSelect(id).size());
	   
	   mav.setViewName("users/userView");
      return mav;
   }
   
   
   //회원등록
   @PostMapping("joinOk")
   public String joinOk(UserVO vo, Model model, @RequestParam("profileImg") MultipartFile profileImg) {
      
      Path filePath = Paths.get(fileRealPath+profileImg.getName()+vo.getId());
      try {
         Files.write(filePath, profileImg.getBytes());
         System.out.println(filePath+"경로에 이미지가 저장됨");
         //getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
      } catch (Exception e) {
         e.printStackTrace();
      }
      String pathName = "\\profiles\\profileImg"+vo.getId();
      int cnt = service.userInsert(vo, pathName);
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
   public ModelAndView userEditOk(UserVO vo, HttpSession session, @RequestParam("profileImg") MultipartFile profileImg) {
      vo.setId((String) session.getAttribute("logId"));
      
      Path filePath = Paths.get(fileRealPath+profileImg.getName()+vo.getId());
      try {
         Files.write(filePath, profileImg.getBytes());
         System.out.println(filePath+"경로에 이미지가 저장됨");
         //getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
      } catch (Exception e) {
         e.printStackTrace();
      }
      String pathName = "\\profiles\\profileImg"+vo.getId();
      service.userUpdate(vo, pathName);
      
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
            session.setAttribute("logImg", rVo.getUser_img());
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
   
   //로그아웃
   @GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); // 세션 제거 = 로그아웃 / 새로운 세션이 할당된다
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
   
   
// ********************* 카카오 로그인 (김진영) *************************************** // 
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수
		// POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// Retrofit2 (안드로이드에서 사용)
		// Okhttp
		// RestTemplate
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "66837c1b56e8c94b59ea0bf0ec332abe");
		params.add("redirect_uri", "http://localhost:9060/users/auth/kakao/callback");
		params.add("code", code);


		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = rt.exchange(""
				+ "https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				kakaoTokenRequest, 
				String.class
		);
		
		
		// 토큰메소드, 사용자정보받는 메소드 
		
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

// 		System.out.println("카카오 엑세스 토큰:" + oauthToken.getAccess_token());
//		return response.getBody();
		 

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답받음
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
		
		// User 오브젝트 : username, password, email..?
		
		System.out.println("카카오 아이디(번호):"+kakaoProfile.id);
		System.out.println("카카오 이메일:"+kakaoProfile.kakao_account.email);
		System.out.println("카카오 닉네임:"+kakaoProfile.properties.nickname);
		
		// 추가적인 회원정보가 필요하면 사용자정보 구성창을 만들어서 입력을 받아야함. 
		
		System.out.println("triptogether서버 유저네임:"+kakaoProfile.kakao_account.email+"_"+kakaoProfile.id);
		System.out.println("triptogether서버 이메일:"+kakaoProfile.kakao_account.email);
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("triptogether서버 패스워드"+garbagePassword);
		
		
		return response2.getBody();
	} 
   
   
}