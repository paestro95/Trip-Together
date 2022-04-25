<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${url}/css/manager/managerMember.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<script src="${url}/js/mypage/myPlan.js"></script>
<script>
$(function(){
	$("#delBtn").click(function(){
			var cnt = 0;
					
			$(".chk").each(function(i, obj){
				if(obj.checked){
					cnt++;
				}
			});
					
			if(confirm("삭제하겠습니까?")){
				$("#memberFrm").submit();
			}
						
			if(cnt <= 0){
				alert("회원을 선택해 주세요.");
				return false;
			}
	});
});


</script>

<!-- top.jspf 공간 확보를 위한 div블록 -->
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>

<div class="mypage_container">

	<div id="mypage_profile">
		<h3 style="font-size: 1.7em; margin-top: 50px;">MANAGER</h3>
		<ul>
			<li><img src="${logImg}" id="mypage_profile_img" /></li>
			<li>${logName}님</li>
			<li><a href="${url}/manager/managerMember">MEMBERS</a></li>
			<li style="margin-top: 190px;"><a href="${url}/users/userEdit">About</a> | <a href="${url}/users/logout">Log Out</a></li>
		</ul>
	</div>

	<!-- ///////////////////////////////////////////// -->

	<div class="member_List">
		<div class="member_header">Trip Together 회원목록</div>
		<form action="${url}/manager/memberDelOK" method="post" id="memberFrm">
			<div>* 전체 회원수 : ${memberTotalNo}</div>
			<ul class="memberList_wrap">
				<li class="list_top">&nbsp;</li>
				<li class="list_top">아이디</li>
				<li class="list_top">비밀번호</li>
				<li class="list_top">이름</li>
				<li class="list_top">연락처</li>
				<li class="list_top">이메일</li>
				<li class="list_top">소개</li>
				<li class="list_top">성별</li>
				<li class="list_top">연령대</li>
				<li class="list_top">가입날짜</li>
				<c:forEach var="vo" items="${list}">
					<li><input type="checkbox" name="noList" value="${vo.id}" class="chk"></li>
					<li><a href="${url}/users/userView?id=${vo.id}">${vo.id}</a></li>
					<li>${vo.pwd}</li>
					<li><a href="${url}/users/userView?id=${vo.id}">${vo.name }</a></li>
					<li>${vo.tel }</li>
					<li><c:if test="${empty vo.email}">&nbsp;</c:if> ${vo.email }</li>
					<li><c:if test="${empty vo.info}">&nbsp;</c:if> ${vo.info}</li>
					<li><c:if test="${vo.gender == 0}">&nbsp;</c:if> <c:if test="${vo.gender == 1 }">남자</c:if> <c:if test="${vo.gender == 2 }">여자</c:if></li>
					<li><c:if test="${vo.age == 0}">&nbsp;</c:if> <c:if test="${vo.age == 1}">10대</c:if> <c:if test="${vo.age == 2}">20대</c:if> <c:if test="${vo.age == 3}">30대</c:if> <c:if test="${vo.age == 4}">40대</c:if> <c:if test="${vo.age == 5}">50대</c:if></li>
					<li>${vo.joindate}</li>
				</c:forEach>
			</ul>
			<div class="delBtn_wrap">
				<button id="delBtn">탈퇴</button>
			</div>
		</form>




	</div>

</div>
<a id="MOVE_TOP_BTN" href="#">
	<img src="${url}/Images/back-to-top.png" style="width: 30px;">
</a>
<script src="${url}/js/inc/top.js"></script>
<script src="${url}/js/underBar.js"></script>
