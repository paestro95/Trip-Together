<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/plan/planCreate.css" type="text/css">
<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />

<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/main/main.js"></script>



<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ba94570565044323eac417fcde53827&libraries=services"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  
<script>
	$( function() {
		$( "#datepicker1" ).datepicker();
		$( "#datepicker2" ).datepicker();
	} );
</script>
</head>
<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
<body onload="mapSetter(${x}, ${y})">

<hr/>
<div class="container">

	<!-- 좌측 planer -->
	<form>
		<div id="planCreate_nav" class="planCreate_item">
			<h2>${loc}</h2>
			
			<input type="text" id="datepicker1" placeholder="출발일" style="text-align:center"/><br/>
			~<br/>
			<input type="text" id="datepicker2" placeholder="도착일" style="text-align:center"/><br/>
			<div id="planCreate_trip_title">여행 일정 리스트</div>
			<div id="planCreate_trip_list">
				<ul id=planCreate_list>
					
				</ul>
			</div>
			<input type="submit" value="저장하기" id="planCreate_save"/>
		</div>
	</form>

	

	<!-- 중앙 MAP -->
	<div id="planCreate_map">
		
		
	</div>
	
	
	<!-- 검색/결과 LIST -->
	<div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                <form onsubmit="searchPlaces(); return false;">
                    키워드 : <input type="text" value="${loc }" id="keyword" size="15"> 
                    <button type="submit">검색하기</button> 
                </form>
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
	
	
</div>
<script src="/js/plan/planCreate.js"></script>
<script src="${url}/js/inc/top.js"></script>
<script src="${url}/js/underBar.js"></script>

</body>
</html>