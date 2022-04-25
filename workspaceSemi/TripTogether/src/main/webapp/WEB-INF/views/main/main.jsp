<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />

<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/bottom.css" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src=https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

	
	<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
	
	<div class="wrap">
		<div class="main_wrap">
			<div class="top_section">
				<c:forEach var="vo" items="${bestPostVO}">
					<div class="weeklyhot_wrap">
						<img src="${vo.photo1}">
					</div>
					<div class="weeklyhot_cover">
						<div class="weeklyhot_phr">이번주 인기 여행지를 구경해보세요.</div>
						<div style="display: flex;">
							<div class="user_main_profile_wrap">
								<div class="user_img"><a href="${url}/users/userView?id=${vo.id}"><img src="${vo.user_img}"></a></div>
								<span><a href="${url}/users/userView?id=${vo.id}">${vo.id}</a></span>
							</div>
							<div class="show_post_btn">
								<button onclick="location.href='${url}/community/communityView?no=${vo.board_no}&id=${vo.id}'">보러가기</button>
								<input type="hidden" value="${vo.board_no}">
							</div>
						</div>
					</div>
					</c:forEach>
				<div class="ad_wrap">
					<ul class="slides"  style="text-align: right;">
						<li><a href="https://www.sungsimdangmall.co.kr/"target='_black'><img src="${url}/Images/ad3.png"></a></li>
					</ul>
				</div>
			</div>
			
			<h3>새로운 여행지<span>  NEW TRAVELOG</span></h3>
			<div class="travelog_img_wrap">
				<ul>
					<c:forEach var="vo" items="${weeklyPostVO}">
						<li>
							<a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}"><img src="${vo.photo1}"></a>
							<div style="display: none;"><a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}">${vo.title}</a></div>
							<input type="hidden" value="${vo.id}">
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	
	

	<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
	</a>
	<script src="${url}/js/underBar.js"></script>
	<script src="${url}/js/main/main.js"></script>
	<script src="${url}/js/login.js"></script>
