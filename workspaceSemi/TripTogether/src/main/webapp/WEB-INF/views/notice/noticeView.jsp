<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="${url}/css/notice/noticeView.css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}css/bottom.css" type="text/css" />

<script>
	function del(){
		if(confirm('삭제할까요?')){
			location.href="/notice/noticeDel?no=${vo.notice_no}";
		}
	}
</script>
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div class="wrap">
	<div class="noticeView_wrap">
		<h1>공지사항</h1>
		<h2>Trip Together의 다양한 소식을 확인하세요!</h2>
		
		<!-- 본문 view(신수진) -->
		<ul class="noticeView_main">
			<li>${vo.notice_no}</li>
			<li>${vo.title}</li>
			<li>${vo.writedate}</li>
			<li>${vo.content}</li>
		</ul>
		<!-- 수정, 목록, 삭제 버튼 wrap(신수진) -->
		<div class="view_menu_wrap">
			<div class="move_list_wrap"><a href="${url}/notice/noticeList">목록</a></div>
			<c:if test="${logId == 'admin'}">
	        	<div class="edit_del_wrap"><a href="${url}/notice/noticeUpdate?no=${vo.notice_no}">수정  |</a><a href="javascript:del()"> 삭제</a></div> <!-- 수정(권지연) -->
	        </c:if>
		</div>
	</div>
</div>