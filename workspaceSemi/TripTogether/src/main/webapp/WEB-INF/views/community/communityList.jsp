<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${url}/css/community/communityList.css" type="text/css" />
<script src="${url}/js/community/communityList.js"></script>
<script src="${url}/js/underBar.js"></script>


<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div class="opadiv" style="background-color: white; width: 100%; height: 100px; color: black"></div>

<div class="wrap">
	<div class="communityList_wrap">
		<h1>여기 같이 가볼까?</h1>
		<div id="underline_img"><img src="${url}/Images/h_underline.png"></div>

		<div class="notice_wrap">
			<a href="${url}/notice/noticeList">
				<span class="material-icons" style="color: gray;">campaign</span>
			</a>
			<marquee direction="left" style="width: 1165px; height: 24px; line-height: 29px; font-size: 14px;">
				<a href="${url}/notice/noticeList" style="color: gray;">${nVO.title}</a>
			</marquee>
		</div>

		<div class="sort_wrap">
			<ul class="category_list region">
				<li style="pointer-events: none;">지역</li>
				<li style="cursor: pointer;">서울</li>
				<li style="cursor: pointer;">경기</li>
				<li style="cursor: pointer;">인천</li>
				<li style="cursor: pointer;">강원</li>
				<li style="cursor: pointer;">충청</li>
				<li style="cursor: pointer;">전라</li>
				<li style="cursor: pointer;">경상</li>
				<li style="cursor: pointer;">제주</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
			</ul>

			<ul class="category_list theme">
				<li style="pointer-events: none;">분류</li>
				<li style="cursor: pointer;">식당</li>
				<li style="cursor: pointer;">카페</li>
				<li style="cursor: pointer;">액티비티</li>
				<li style="cursor: pointer;">숙박</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
				<li style="pointer-events: none;">&nbsp;</li>
			</ul>

			<ul class="category_list hashtag">
				<li style="pointer-events: none;">테마</li>
				<li style="cursor: pointer;">#혼자여행</li>
				<li style="cursor: pointer;">#포토존</li>
				<li style="cursor: pointer;">#힐링</li>
				<li style="cursor: pointer;">#가족여행</li>
				<li style="cursor: pointer;">#데이트</li>
				<li style="cursor: pointer;">#뷰맛집</li>
				<li style="cursor: pointer;">#힙한</li>
				<li style="cursor: pointer;">#애견동반</li>
				<li style="cursor: pointer;">#이국적</li>
				<li style="cursor: pointer;">#레트로</li>
				<li style="cursor: pointer;">#감성</li>
			</ul>

			<button class="select_reset" style="float: left; padding-top: 7px;">
				<span class="material-icons" style="font-size: 20px; color: gray; cursor: pointer;">restart_alt</span>
			</button>
			<div class="select_cate_wrap"></div>
		</div>



		<div class="container_card_wrap">
			<div class="hot_new_writeBtn_wrap">
				<div class="hot_new_wrap">
					<a href="#" id="hotBtn">인기</a>
					|
					<a href="#" id="newBtn">신규</a>
				</div>
				<div class="writeBtn_wrap">
					<c:if test="${logStatus == 'Y'}">
						<a href="${url}/community/communityWrite">
							<input type="button" value="글쓰기" />
						</a>
					</c:if>
				</div>
			</div>

			
			<c:forEach var="vo" items="${list}">
				<div class="${vo.theme} card_wrap ${vo.region} &nbsp ${vo.tags}">
					<div class="card_writer_wrap">
						<div class="user_img_wrap">
							<a href="${url}/users/userView" class="user_img">
								<img src="${vo.user_img}" />
							</a>
						</div>
						<a href="${url}/users/userView" class="id">${vo.id}</a>
						&#183;
						<button class="followBtn">팔로우</button>
						<p>${vo.info}</p>
					</div>
					<div class="card_content_wrap">
						<div class="card_main_img_wrap">
							<a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}">
								<img src="${vo.photo1}" class="card_img" />
							</a>
						</div>
						<aside class="side_menu">
							<c:choose>
								<%-- 로그인 상태일 때, --%>
								<c:when test="${logStatus == 'Y' }">
									<button>
										<span class="material-icons like" style="cursor: pointer; vertical-align: middle;">favorite_border</span> ${vo.likes}
									</button>
									<button>
										<span class="material-icons save" style="cursor: pointer; vertical-align: middle;">bookmark_border</span> ${vo.wish}
									</button>
								</c:when>
								<%-- 로그인 상태가 아닐 때, alert + 로그인 div가 오른쪽에 나타남 --%>
								<c:otherwise>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">favorite_border</span> ${vo.likes}
									</button>
									<button>
										<span class="material-icons" style="cursor: pointer; vertical-align: middle;" onclick="btn_notlogin();">bookmark_border</span> ${vo.wish}
									</button>
								</c:otherwise>
							</c:choose>
							<%-- 댓글 아이콘 클릭 시 해당 게시물 view 페이지로 이동 --%>
							<button>
								<span class="material-icons comment" style="cursor: pointer; vertical-align: middle;" onclick="location.href='${url}/community/communityView?no=${vo.board_no}' " >chat_bubble_outline</span> ${vo.comment }
							</button>
						</aside>
					</div>
				</div>
			</c:forEach>

			
			

			
			






		</div>
	</div>
</div>
