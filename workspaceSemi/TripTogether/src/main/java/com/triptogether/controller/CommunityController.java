package com.triptogether.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.triptogether.service.CommunityService;
import com.triptogether.service.FollowService;
import com.triptogether.service.LikesService;
import com.triptogether.service.NoticeService;
import com.triptogether.service.UserService;
import com.triptogether.service.WishService;
import com.triptogether.vo.CommunityVO;
import com.triptogether.vo.FollowVO;
import com.triptogether.vo.LikesVO;
import com.triptogether.vo.NoticeVO;
import com.triptogether.vo.UserVO;
import com.triptogether.vo.WishVO;

@RestController
public class CommunityController {
	
	@Value("/communityImg/")
	private String fileRealPath;
	
	@Inject
	CommunityService service;
	
	@Inject
	NoticeService nService;
	
	@Inject
	UserService uService;
	
	@Inject
	FollowService fservice;
	
	@Inject
	WishService wservice;
	
	@Inject
	LikesService lService;

	// 커뮤니티 리스트 페이지
	@GetMapping("/community/communityList")
	public ModelAndView communityList(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String logId = (String)session.getAttribute("logId");
		
		// DB처리
		mav.addObject("list", service.communityList(logId));
		mav.addObject("newList", service.communityNewList(logId));
		
		
		NoticeVO nVO = nService.latestNoticeSelect();
		mav.addObject("nVO", nVO);
		
		
		mav.setViewName("community/communityList");
		return mav;
	}
	
	// 커뮤니티 글등록 페이지로 이동
	@GetMapping("/community/communityWrite")
	public ModelAndView CommunityWrite() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("community/communityWrite");
		return mav;
	}

	// 커뮤니티 글등록 DB
	@PostMapping("/community/communityWriteOk")
	public ResponseEntity<String> communityWriteOk(CommunityVO vo, HttpServletRequest request, @RequestPart("photo") List<MultipartFile> p1) {
		vo.setId((String) request.getSession().getAttribute("logId"));
		
		//이미지경로값 중복을 막기 위한 특수값!
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");
		String formatedNow = now.format(formatter);

		//Path filePath;
		String pathName[] = new String[4];
		for(int i=0; i<pathName.length; i++) pathName[i] = null;	//null값으로 '이미지경로값'배열 초기화
		

		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));

		try {
			String[] locations = request.getParameterValues("location");
			String[] location_addrs = request.getParameterValues("location_addr");
			String[] contents = request.getParameterValues("content");
			
			int cnt = 1;
			for(MultipartFile f : p1) {
				if(cnt == 1) {
					vo.setLocation1(locations[0]);
					vo.setLocation_addr1(location_addrs[0]);
					vo.setContent1(contents[0]);
					
					//경로명=파일명 설정 후 해당 경로에 파일(이미지) 업로드 + DB처리
					pathName[0] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_1";
					try {
						File dest = new File(pathName[0]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[0]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 2) {
					vo.setLocation2(locations[1]);
					vo.setLocation_addr2(location_addrs[1]);
					vo.setContent2(contents[1]);
					
					pathName[1] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_2";
					try {
						File dest = new File(pathName[1]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[1]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 3) {
					vo.setLocation3(locations[2]);
					vo.setLocation_addr3(location_addrs[2]);
					vo.setContent3(contents[2]);
					
					pathName[2] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_3";
					try {
						File dest = new File(pathName[2]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[2]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 4) {
					vo.setLocation4(locations[3]);
					vo.setLocation_addr4(location_addrs[3]);
					vo.setContent4(contents[3]);
					
					pathName[3] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_4";
					try {
						File dest = new File(pathName[3]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[3]);
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
				cnt++;
			}
						
			// 커뮤니티 등록 성공시 커뮤니티 리스트로 이동
			service.communityInsert(vo, pathName);
			String msg = "<script>alert('글이 등록되었습니다.');location.href='/community/communityList';</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 공지사항 등록 실패시 글등록 폼으로 이동
			String msg = "<script>alert('글등록이 실패하였습니다.');history.back();</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}


	// 커뮤니티뷰 communityView
	@RequestMapping("/community/communityView")
	public ModelAndView communityView(int no, String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		var logId = (String)session.getAttribute("logId");
		//팔로우 220420 지연님 추가
		FollowVO fvo= new FollowVO();
		fvo.setFollow_id(id);
		fvo.setId((String)session.getAttribute("logId"));
		int cnt=fservice.oxFollow(fvo);
		if(cnt>0) {
			session.setAttribute("isFollow", "F");
		} else {
			session.setAttribute("isFollow", "U");
		}//////
		
		// 좋아요
		LikesVO lvo = new LikesVO();
		lvo.setId(logId);
		lvo.setBoard_no(no);
		int lCnt = lService.oxLikes(lvo);
		System.out.println("lCnt"+lCnt);
		if(lCnt > 0) {
			session.setAttribute("isLikes", "L");
		}else {
			session.setAttribute("isLikes", "U");
		}
		
		//wish
        WishVO wvo = new WishVO();
        wvo.setBoard_no(no);
        wvo.setId(logId);
        int wcnt=wservice.oxWish(wvo);
        if(wcnt>0) {
            session.setAttribute("isWish", "W");
        }else {
            session.setAttribute("isWish", "N");
        }
		
		
		
		mav.addObject("bp", service.firstPhotoSelect(id));
		mav.addObject("vo", service.communitySelect(no));
		mav.addObject("uVo", uService.userSelect(id));
		
		mav.setViewName("community/communityView");
		return mav;
	}
	
	// 커뮤니티 게시물 삭제
		@GetMapping("/community/communityDel")
		public ResponseEntity<String>  communityDel(int no, HttpSession session) {
			
			ResponseEntity<String> entity = null;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
			
			String id = (String) session.getAttribute("logId");
			int result = service.communityDelete(no, id);
			
			try {
				if (result > 0) { // 게시물 삭제 성공시 공지사항 리스트로
					String msg = "<script>alert('삭제가 완료되었습니다.'); location.href='/community/communityList';</script>";
					entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
				} else { 
					throw new Exception();
				}
			}catch(Exception e) {
				e.printStackTrace();
				// 게시물 삭제 실패시 해당 게시물로
				String msg = "<script>alert('글삭제를 실패하였습니다.');history.back();</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST);
			}
			return entity;
		}


	// 커뮤니티 글수정 페이지로 이동
	@GetMapping("/community/communityUpdate")
	public ModelAndView CommunityUpdate(int no, String tags) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", service.communitySelect(no));
		mav.setViewName("community/communityUpdate");
		return mav;
	}

	// 커뮤니티 글수정DB
	@PostMapping("/community/communityUpdateOk")
	public ResponseEntity<String> communityUpdateOk(CommunityVO vo, HttpSession session, HttpServletRequest request, @RequestPart("photo") List<MultipartFile> p1) {
		vo.setId((String) session.getAttribute("logId")); // 글쓴이
		
		//이미지경로값 중복을 막기 위한 특수값!
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");
		String formatedNow = now.format(formatter);

		//Path filePath;
		String pathName[] = new String[4];
		for(int i=0; i<pathName.length; i++) pathName[i] = null;	//null값으로 '이미지경로값'배열 초기화
		
		

		ResponseEntity<String> entity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		try {
			String[] locations = request.getParameterValues("location");
			String[] location_addrs = request.getParameterValues("location_addr");
			String[] contents = request.getParameterValues("content");

			int cnt = 1;
			for(MultipartFile f : p1) {
				if(cnt == 1) {
					vo.setLocation1(locations[0]);
					vo.setLocation_addr1(location_addrs[0]);
					vo.setContent1(contents[0]);
					
					//경로명=파일명 설정 후 해당 경로에 파일(이미지) 업로드 + DB처리
					pathName[0] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_1";
					try {
						File dest = new File(pathName[0]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[0]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 2) {
					vo.setLocation2(locations[1]);
					vo.setLocation_addr2(location_addrs[1]);
					vo.setContent2(contents[1]);
					
					pathName[1] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_2";
					try {
						File dest = new File(pathName[1]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[1]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 3) {
					vo.setLocation3(locations[2]);
					vo.setLocation_addr3(location_addrs[2]);
					vo.setContent3(contents[2]);
					
					pathName[2] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_3";
					try {
						File dest = new File(pathName[2]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[2]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(cnt == 4) {
					vo.setLocation4(locations[3]);
					vo.setLocation_addr4(location_addrs[3]);
					vo.setContent4(contents[3]);
					
					pathName[3] = "\\communityImg\\"+vo.getId()+"_"+formatedNow+"_4";
					try {
						File dest = new File(pathName[3]);
						f.transferTo(dest);
						vo.setPhoto1(pathName[3]);
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
				cnt++;
			}
			
			service.communityUpdate(vo, pathName);
			String msg = "<script>alert('글이 수정되었습니다.'); location.href='/community/communityView?no="+vo.getBoard_no()+"&id="+vo.getId()+"';</script>";
				entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK); // 성공시 수정완료게시물로 이동

		} catch (Exception e) {
			e.printStackTrace();
			String msg = "<script>alert('글수정을 실패했습니다.');history.go(-1);</script>";
			entity = new ResponseEntity<String>(msg, headers, HttpStatus.BAD_REQUEST); // 실패시 다시 수정폼으로 -1
		}
		return entity;
	}


	/*
	 * @RequestMapping(value = "main/post/{id}") public String
	 * post(@PathVariable("id") int id, Model model) throws Exception { String
	 * userId = SecurityContextHolder.getContext().getAuthentication().getName();
	 * User user = userService.findByUserId(userId);
	 * 
	 * model.addAttribute("postuserid", postService.findById(id).getUser().getId());
	 * model.addAttribute("p", postService.findById(id)); model.addAttribute("img",
	 * piService.findBypostId(id));
	 * 
	 * return "main/post"; }
	 */
}