<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />

<link rel="stylesheet" href="${url}/css/main/main.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/bottom.css" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src=https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
	Kakao.init('1fd47e545b38a1453a50346ccb8c9073');
	function KakaoLogin() {
		Kakao.Auth.login({
			success : function(authObj) {
				alert(JSON.stringify(authObj))
			},
			fail : function(err) {
				alert(JSON.stringify(err))
			},
		});
	}
	//function KakaoLogout(){
	//   if (!Kakao.Auth.getAccessToken()) {
	//      alert('Not logged in.')
	//      return
	//    }
	//    Kakao.Auth.logout(function() {
	//      alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken())
	//    
	//    });
	//}

	function KakaoUnlink() {
		Kakao.API.request({
			url : '/v1/user/unlink',
			success : function(res) {
				alert('success: ' + JSON.stringify(res))
			},
			fail : function(err) {
				alert('fail: ' + JSON.stringify(err))
			},
		});
	}
	var klogin = document.querySelector(".kakao_login");
	var klogout = document.querySelector(".kakao_logout");

	if (!Kakao.Auth.getAccessToken()) {
		klogin.style.display = "block";
		klogout.style.display = "none";
	} else {
		klogin.style.display = "none";
		klogout.style.display = "block";
	}
</script>

	<div style="background: black; z-index: -1; position: relative;">
		<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>
	</div>
	<div class="wrap">
		<div class="main_wrap">
			<div class="top_section">
				<c:forEach var="vo" items="${bestPostVO}">
					<div class="weeklyhot_wrap">
						<img src="${vo.photo1}">
					</div>
					<div class="weeklyhot_cover">
						<div class="weeklyhot_phr">이번주 인기 여행지를 구경해보세요</div>
						<div style="display: flex;">
							<div class="user_main_profile_wrap">
								<div class="user_img"><a href="${url}/users/userView?id=${vo.id}"><img src="${vo.user_img}"></a></div>
								<span><a href="${url}/users/userView?id=${vo.id}">${vo.id}</a></span>
							</div>
							<div class="show_post_btn">
								<button onclick="location.href='${url}/community/communityView?no=${vo.board_no}&id=${vo.id}'">보러가기</button>
								<input type="hidden" value="${vo.board_no}">
							</div>
						</div>
					</div>
					</c:forEach>
				<div class="ad_wrap">
					<ul class="slides"  style="text-align: right;">
						<li><img src="${url}/Images/ad2.jpeg"></li>
					</ul>
				</div>
			</div>
			
			<h3>이번주 여행기 <span>TRAVELOG</span></h3>
			<div class="travelog_img_wrap">
				<ul>
					<c:forEach var="vo" items="${weeklyPostVO}">
						<li>
							<a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}"><img src="${vo.photo1}"></a>
							<div style="display: none;"><a href="${url}/community/communityView?no=${vo.board_no}&id=${vo.id}">${vo.title}</a></div>
							<input type="hidden" value="${vo.id}">
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	
	

	<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
	</a>
	<script src="${url}/js/underBar.js"></script>
	<script src="${url}/js/main/main.js"></script>
	<script src="${url}/js/login.js"></script>
