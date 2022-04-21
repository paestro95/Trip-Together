<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${url}/css/manager/managerMember.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<script src="${url}/js/mypage/myPlan.js"></script>

	<!-- top.jspf 공간 확보를 위한 div블록 -->
	<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

	<div class="mypage_container">

		<div id="mypage_profile">
			<h3 style="font-size: 1.7em; margin-top: 50px;">MANAGER</h3>
			<ul>
				<li>
						<img src="${logImg}" id="mypage_profile_img" />
				</li>
				<li>${logName}님</li>
				<li><a href="${url}/manager/managerMember">MEMBERS</a></li>
				<li style="margin-top: 190px;"><a href="${url}/users/userEdit">About</a> | <a href="${url}/users/logout">Log Out</a></li>
			</ul>
		</div>

		<!-- ///////////////////////////////////////////// -->

		<div id="myPlan_List">
			Members<br />
			<br />
			
			
			
		</div>

	</div>

	<script src="${url}/js/inc/top.js"></script>
	<script src="${url}/js/underBar.js"></script>