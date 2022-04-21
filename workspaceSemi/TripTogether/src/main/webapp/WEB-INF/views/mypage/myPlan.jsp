<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPlan</title>
<link rel="stylesheet" href="${url}/css/mypage/myPlan.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<script src="${url}/js/mypage/myPlan.js"></script>
<script>
		function planDel() {
			var no = $("#plan_no").val();
			if (confirm("삭제하시겠습니까?")) {
				// 확인버튼 선택시
				location.href = "${url}/mypage/planDel?no="+no;
			}
		}
</script>
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

		<div id="myPlan_List">
			PLAN<br />
			<br />
			

			<c:forEach var="vo" items="${list}">
			<div class="hot_post_card_wrap">
				<input type="hidden" value=${vo.plan_no } id="plan_no"/>
				<ul class="place_list">
					<li><a href="javascript:planDel()">${vo.start_date}~${vo.end_date}</a></li>
					<c:forEach var="index" items="${vo.splitLoc}">
						<li>${index}</li>
					</c:forEach>
					
				</ul>
			</div>
			</c:forEach>
			
			
		</div>

	</div>

	<script src="${url}/js/inc/top.js"></script>
	<script src="${url}/js/underBar.js"></script>
	
</body>
</html>