<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${url}/css/notice/noticeWrite.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}css/bottom.css" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="${url}/js/notice/noticeWrite.js"></script>
<script src="${url}/js/main/main.js"></script>
<script src="${url}/js/underBar.js"></script>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
	<!-- 클래식 에디터 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
	<title>Home</title>
	
	<!-- 넓이 높이 조절 -->
	<style>
	.ck.ck-editor {
    	max-width: 800px;
	}
	.ck-editor__editable {
	    min-height: 500px;
	}
	</style>
</head>
<script>
$(function() {

	CKEDITOR.replace("#content"); //ckeditor

	$("#noticeForm").submit(function() {	//noticeForm함수
		if ($("#title").val() == '') {		//제목 미입력시 alert
			alert("제목을 입력하세요");
			return false;
		}
		if ($("#content".val() == '') {	//내용 미입력시 alert
			alert("내용을 입력하세요");
			return false;
		}
	});
	
});
</script>
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<div class="noticeWrite_wrap">
	<form method="post" action="/notice/noticeWriteOk" id="noticeForm">
		<h1>공지사항 작성</h1>
		<input type="text" name="title" id="title" placeholder="제목을 입력하세요.">
		<hr />

		<div id="classic">
			<textarea name="content" id="content" placeholder="내용을 입력하세요."></textarea>
		
		<script>		
				    ClassicEditor
				        .create( document.querySelector( '#content' ))
				        .catch( error => {
				            console.error( error );
				        } );
		</script>
		</div>
		<div>
			<input type="submit" value="등록" id="upload">
		</div>
	</form>
</div>