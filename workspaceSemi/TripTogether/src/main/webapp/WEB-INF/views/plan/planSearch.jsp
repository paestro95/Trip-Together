<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${url}/css/plan/planSearch.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />

</head>
<body>

	<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
	
	<div class="container">

		<p id="planSearch_title">어디로 여행을 떠나시나요??</p>
		<p id="planSearch_title2">여행지를 목록에서 직접 선택해주세요.</p>

		<!-- <input type="text" id="planSearch_search"/> -->
		<br />
		<br />
		<br />

		<div id="planSearch_imgList">
			<ul>
				<li><a href="${url}/plan/planCreate/서울&37.566826&126.9786567">
						<img src="/Images/서울.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/경기&37.831453&127.5095566">
						<img src="/Images/경기.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/인천&37.456058&126.705224">
						<img src="/Images/인천.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/강원&37.752173&128.8758965">
						<img src="/Images/강원.png" />
					</a></li>
			</ul>
			<ul>
				<li><a href="${url}/plan/planCreate/충청&36.4466056&127.1190038">
						<img src="/Images/충청.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/전라&35.160138&126.851608">
						<img src="/Images/전라.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/경상&35.1798283&129.0750262">
						<img src="/Images/경상.png" />
					</a></li>
				<li><a href="${url}/plan/planCreate/제주&33.4889899&126.4982072">
						<img src="/Images/제주.png" />
					</a></li>
			</ul>
		</div>
	</div>
	
	<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
	</a>

	<script src="${url}/js/plan/planSearch.js"></script>
	<script src="${url}/js/inc/top.js"></script>
	<script src="${url}/js/underBar.js"></script>
</body>
</html>