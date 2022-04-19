<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/css/main/main.css" type="text/css"/>
<link rel="stylesheet" href="/css/notice/noticeList.css" type="text/css"/>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div class="noticeList_wrap">
	<h1>공지사항</h1>
	<h2>Trip Together의 다양한 소식을 확인하세요!</h2>
	<ul id="nList"> <!-- 공지사항 리스트 아이디 nList -->
		<li>번호</li>
		<li>제목</li>
		<li>작성자</li>
		<li>등록일</li>
		
		<c:forEach var="vo" items="${lst}">
			<li>${vo.notice_no}</li>
			<li><a href="${url}/notice/noticeView?no=${vo.notice_no}">${vo.title}</a></li>
			<li>${vo.id}</li>
			<li>${vo.writedate}</li>
		</c:forEach> 
	
	</ul>
	<c:if test="${logId == 'admin'}">
	<div class="writeBtn">
		<a href="${url}/notice/noticeWrite">
			<input type="button" value="글쓰기" />
		</a>
	</div>
	</c:if>
	
	
	
	 <!-- 페이징 ----------------------------------------------------------->
	<ul class="paging">
		<!-- 이전페이지 -->
		<c:if test = "${pVO.pageNum==1}">
			<li><a href="#">prev</a></li>
		</c:if>
		<c:if test = "${pVO.pageNum>1}">
			<li><a href="/notice/noticeList?pageNum=${pVO.pageNum-1}<c:if test='${pVO.searchWord!=null }'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">prev</a></li>
		</c:if>
		 <!-- 페이지번호 -->
        <c:forEach var="p" begin="${pVO.startPage}" end="${pVO.startPage+pVO.onePageCount-1}">
             <!-- 총페이지수 보다 출력할 페이지번호가 작을때 -->          
             <c:if test="${p<=pVO.totalPage}">
             	<c:if test="${p==pVO.pageNum }">
             	   <li style="font-weight: bold">
             	</c:if>
             	<c:if test="${p!=pVO.pageNum }">
             	   <li>
             	</c:if>
             	<a href="/notice/noticeList?pageNum=${p}<c:if test='${pVO.searchWord!=null }'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">${p}</a></li>
           	 </c:if>           	 
        </c:forEach>
        <!-- 다음페이지 -->
        <c:if test="${pVO.pageNum==pVO.totalPage }">
			<li>next</li>		
		</c:if>
		<c:if test="${pVO.pageNum<pVO.totalPage }">
			<li><a href="/notice/noticeList?pageNum=${pVO.pageNum+1}<c:if test='${pVO.searchWord!=null }'>&searchKey=${pVO.searchKey}&searchWord=${pVO.searchWord}</c:if>">next</a></li>
		</c:if>
	</ul>	 
</div>