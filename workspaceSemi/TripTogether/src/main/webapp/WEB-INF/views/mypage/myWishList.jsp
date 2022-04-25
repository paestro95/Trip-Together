<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${url}/css/mypage/myPlan.css" type="text/css">
<link rel="stylesheet" href="${url}/css/mypage/myPlace.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/mypage/myWishList.css" type="text/css" />

<!-- top.jspf 공간 확보를 위한 div블록 -->
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

<div class="mypage_container">

	<div id="mypage_profile">
		<h3 style="font-size: 1.7em; margin-top: 50px;">MY PAGE</h3>
		<ul>
			<li><a href="/users/userView?id=${logId}">
					<img src="${logImg}" id="mypage_profile_img" />
				</a></li>
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
		WISH LIST<br /> <br />

		<ul>
			<c:forEach var="wVO" items="${wList}">
				<li>
					<div class="card_wrap seoul">
						<div class="card_writer_wrap">
							<a href="${url}/users/userView?id=${wVO.id}">
								<span class="user_img"> <img src="${wVO.user_img }" />
								</span>
							</a>
							<a href="${url}/users/userView?id=${wVO.id}">
								<span class="id">${wVO.id }</span>
							</a>
						</div>

						<div class="card_main_img_wrap">
							<a href="${url}/community/communityView?no=${wVO.board_no}&id=${wVO.id}">
								<img src="${wVO.photo1 }" class="card_img" />
							</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
</a>
<script src="${url}/js/inc/top.js"></script>
<script src="${url}/js/underBar.js"></script>