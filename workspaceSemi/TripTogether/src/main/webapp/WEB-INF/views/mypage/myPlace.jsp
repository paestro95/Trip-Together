<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPlace</title>
<link rel="stylesheet" href="${url}/css/mypage/myPlan.css" type="text/css">
<link rel="stylesheet" href="${url}/css/mypage/myPlace.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}css/bottom.css" type="text/css" />
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
				<li>${logName} 님</li>
				<li>팔로워 100 | 팔로잉 100</li>
				<li><a href="${url}/mypage/myPlan">MY PLAN</a></li>
				<li><a href="${url}/mypage/myPlace">PLACE</a></li>
				<li><a href="${url}/mypage/myWishList">WISH LIST</a></li>
				<li><a href="${url}/mypage/myFollowing">FOLLOWING</a></li>
				<li style="margin-top: 60px;"><a href="${url}/users/userEdit">About</a> | <a href="${url}/users/logout">Log Out</a></li>
			</ul>
		</div>

		<!-- ///////////////////////////////////////////// -->

		<div id="myPlace_imgList">
		PLACE<br/><br/>
			<ul>
				<li><img src="/Images/서울.png"></li>
				<li><img src="/Images/강원.png"></li>
				<li><img src="/Images/경기.png"></li>
			</ul>
		</div>





	</div>

	<script src="${url}/js/inc/top.js"></script>
	<script src="${url}/js/underBar.js"></script>
</body>
</html>