<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />

<html>
<meta charset="UTF-8">
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
</head>
<body>

	<div style="background: black; z-index: -1; position: relative;">
		<div class="opadiv" style="background-color: #EBEBEB; width: 100%; height: 100px"></div>


		<div class="opadiv" style="background-color: lightblue; width: 100%; height: 600px">
			<img src="/Images/temp_main.PNG" style="width: 100%; height: 100%;">
		</div>
		<div class="opadiv" style="background-color: lightgreen; width: 100%; height: 600px">
			
		</div>
	</div>

	<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
	</a>
	<script src="${url}/js/underBar.js"></script>
	<script src="${url}/js/main/main.js"></script>
	<script src="${url}/js/login.js"></script>
</body>
</html>