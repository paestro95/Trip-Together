<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPlace</title>
<link rel="stylesheet" href="${url}/css/mypage/myPlan.css" type="text/css">
<link rel="stylesheet" href="${url}/css/mypage/myPlace.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/mypage/myWishList.css" type="text/css" />
<script src="${url}/js/community/communityList.js"></script>
</head>
<body>

	<!-- top.jspf 공간 확보를 위한 div블록 -->
	<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

	<div class="mypage_container">

		<div id="mypage_profile">
			<h3 style="font-size: 1.7em; margin-top: 50px;">MY PAGE</h3>
			<ul>
				<li>
					<a href="/users/userView?id=${logId}">
						<img src="${logImg}" id="mypage_profile_img" /></a>
				</li>
				<li>${logName}님</li>
				<li>팔로워 ${f_erCnt} | 팔로잉 ${f_ingCnt}</li>
				<li><a href="${url}/mypage/myPlan">MY PLAN</a></li>
				<li><a href="${url}/mypage/myWishList">WISH LIST</a></li>
				<li><a href="${url}/mypage/myFollowing">FOLLOWING</a></li>
				<li style="margin-top: 60px;"><a href="${url}/users/userEdit">About</a> | <a href="${url}/users/logout">Log Out</a></li>
			</ul>
		</div>

		<!-- ///////////////////////////////////////////// -->

		<div id="myWishList_imgList">
		WISH LIST<br/><br/>
			
			<ul>
			<c:forEach var="wVO" items="${wList}">
				
				<li>
					<div class="card_wrap seoul">
					<a href="${url}/community/communityView?no=${wVO.board_no}&id=${wVO.id}">
						<div class="card_writer_wrap">
							<span class="user_img">
								<img src="${wVO.user_img }" />
							</span>
						<span class="id">${wVO.id }</span>
						</div>
						<div class="card_content_wrap">
							<div class="card_main_img_wrap">
							<span>
								<img src="${wVO.photo1 }" class="card_img" />
							</span>
							</div>
						</div>
					</a>
					</div>
				
				</li>
				
			</c:forEach>
			</ul>
			
			
		</div>

	</div>

	<script src="${url}/js/inc/top.js"></script>
	<script src="${url}/js/underBar.js"></script>
</body>
</html>