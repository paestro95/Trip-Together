package com.triptogether.controller;

import java.io.File;
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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triptogether.service.CommunityService;
import com.triptogether.service.FollowService;
import com.triptogether.service.PlanService;
import com.triptogether.service.UserService;
import com.triptogether.vo.FollowVO;
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
	
	@Inject
	FollowService fservice;

	// 페이지 이동 (임시) : 로그인 폼
	@GetMapping("login")
	public String login() {
		return "users/login";
	}

	// 페이지 이동 (임시) : 회원가입 폼
	@GetMapping("joinForm")
	public String joinForm() {
		return "users/joinForm";
	}

	// 페이지 이동 (임시) : 회원정보수정 폼
	@GetMapping("userEdit")
	public ModelAndView userEdit(HttpSession session) {
		String id = (String) session.getAttribute("logId");

		UserVO vo = service.userSelect(id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);

		mav.setViewName("users/userEdit");
		return mav;
	}

	// 페이지 이동 (임시) : 회원 프로필 정보 userView
	@GetMapping("userView")
	public ModelAndView userView(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		//팔로우 추가 220420 권지연
 		FollowVO fvo= new FollowVO();
 		fvo.setFollow_id(id);
 		fvo.setId((String)session.getAttribute("logId"));
 		int cnt= fservice.oxFollow(fvo);
 		if(cnt>0) {
 			session.setAttribute("isFollow", "F");
 		}else {
 			session.setAttribute("isFollow", "U");
 		}	 		
 		mav.addObject("fSize", fservice.selectFollowerList(fvo.getFollow_id()).size());
		

		// DB처리
		// mav.addObject("uView", service.userView(id));
		// mav.addObject("list", ?service.bestList(id));
		// mav.addObject("list", p_service.planList(id));

		/* 220419 수진님 추가+수정 */
		mav.addObject("vo", service.userSelect(id));
		mav.addObject("hList", cService.hotPostSelect(id));
		mav.addObject("pList", cService.userPostAllSelect(id));
		mav.addObject("pSize", cService.userPostAllSelect(id).size());

		mav.setViewName("users/userView");
		return mav;
	}

	// 회원등록
	@PostMapping("joinOk")
	public String joinOk(UserVO vo, Model model, @RequestParam("profileImg") MultipartFile profileImg,
			@RequestParam("kakaoImg") String kakaoImg) {
		Path filePath;
		String pathName;
		if (kakaoImg.length() > 0) {
			int kakao_cnt = service.userInsert(vo, kakaoImg);
			model.addAttribute("cnt", kakao_cnt);
			return "users/joinResult";
		}
		
		if(profileImg.isEmpty()) {
			pathName = "\\profiles\\default_profile.jpg";
			int cnt = service.userInsert(vo, pathName);
			model.addAttribute("cnt", cnt);
			return "users/joinResult";
		}
		
		filePath = Paths.get(fileRealPath + profileImg.getName() + vo.getId());
		try {
			Files.write(filePath, profileImg.getBytes());
			System.out.println(filePath + "경로에 이미지가 저장됨");
			// getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathName = "\\profiles\\profileImg" + vo.getId();
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

	// 회원정보 수정
	@PostMapping("userEditOk")
	public ModelAndView userEditOk(UserVO vo, HttpSession session, @RequestParam("kakaoImg") String kakaoImg,
			@RequestParam("profileImg") MultipartFile profileImg) {
		vo.setId((String) session.getAttribute("logId"));
		ModelAndView mav = new ModelAndView();
		
		Path filePath;
		String pathName;
		if(kakaoImg.length()>0) {
			int cnt = service.userUpdate(vo, kakaoImg);
			session.setAttribute("logImg", kakaoImg);
			mav.setViewName("mypage/myPlan");
			return mav;
		}
		
		if(profileImg.isEmpty()) {
			pathName = "\\profiles\\default_profile.jpg";
			int cnt = service.userUpdate(vo, pathName);
			//mav.setViewName(pathName);
			mav.setViewName("redirect:../mypage/myPlan");
			//model.addAttribute("cnt", cnt);
			session.setAttribute("logImg", pathName);
			return mav;
		}
		

		filePath = Paths.get(fileRealPath + profileImg.getName() + vo.getId());
		try {
			Files.write(filePath, profileImg.getBytes());
			System.out.println(filePath + "경로에 이미지가 저장됨");
			// getBytes가 이미지 실체고 이걸 파일로 변환해서 저장해야한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		pathName = "\\profiles\\profileImg" + vo.getId();
		service.userUpdate(vo, pathName);

		session.setAttribute("logImg", pathName);
		mav.setViewName("redirect:../mypage/myPlan");
		return mav;
	}

	// 로그인 (임시)
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
				String msg = "<script> location.href='/main';</script>";
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

	// 로그아웃
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate(); // 세션 제거 = 로그아웃 / 새로운 세션이 할당된다
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}

	// ********************* 회원정보삭제 (김진영) *************************************** //
		// 회원정보삭제
		@GetMapping("userDel")
		public String delete(HttpSession session) {
			String id = (String) session.getAttribute("logId");
			service.userDelete(id);
			session.invalidate();
			return "redirect:/";
		}

	// ********************* 카카오 로그인 (김진영) *************************************** // 

		@GetMapping("auth/kakao/callback")
		public ModelAndView kakaoCallback(String code, HttpSession session) { // Data를 리턴해주는 컨트롤러함수 // POST 방식으로 key=value
						
			RestTemplate rt = new RestTemplate();
			// HttpHeader 오브젝트 생성
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// HttpBody 오브젝트 생성 MultiValueMap<String, String> params = new
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", "66837c1b56e8c94b59ea0bf0ec332abe");
			params.add("redirect_uri", "http://localhost:9060/users/auth/kakao/callback");
			params.add("code", code);

			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기 HttpEntity<MultiValueMap<String,
			HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

			// Http 요청하기 - POST방식으로 - 그리고 response 변수를 응답받음
			ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
					kakaoTokenRequest, String.class);

			// 토큰메소드, 사용자정보받는 메소드
			// Gson, Json Simple, ObjectMapper ObjectMapper
			ObjectMapper objectMapper = new ObjectMapper();
			OAuthToken oauthToken = null;
			try {
				oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			// System.out.println("카카오 엑세스 토큰:" + oauthToken.getAccess_token());
			// return response.getBody();

			RestTemplate rt2 = new RestTemplate();

			// HttpHeader 오브젝트 생성
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

			// Http 요청하기 - POST방식 사용, response 변수의 응답받음
			ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
					kakaoProfileRequest2, String.class);

			// JsonParser parser = new JsonParser();

			ObjectMapper obj = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			KakaoProfile info = null;
			String url = null;

			try {
				info = obj.readValue(response2.getBody(), KakaoProfile.class);
				session.setAttribute("kakao", info);
				session.setAttribute("logId", info.getId());
				session.setAttribute("logImg", info.getProperties().profile_image);
				session.setAttribute("logName", info.getProperties().nickname);
				session.setAttribute("logStatus", "Y");

				System.out.println("세션에 저장됨->>>>" + info);
				// System.out.println(info.properties.nickname);
				int cnt = service.idCheck(info.getId());
				if (cnt == 0) {
					url = "redirect:/users/joinForm";
				} else {
					url = "redirect:/";
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			ModelAndView mav = new ModelAndView();
			mav.setViewName(url);
			System.out.println(response2.getBody());
			return mav;
		}

}