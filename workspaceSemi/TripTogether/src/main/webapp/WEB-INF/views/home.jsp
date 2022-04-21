<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<html>
<meta charset="UTF-8">
<link rel="stylesheet" href="${url}/css/home/home.css" type="text/css" />
<link rel="stylesheet" href="${url}/css/inc/bottom.css" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src=https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js>
	
</script>


<script>
	$(function() {
		$("#top").remove();
		var loginbtn = document.querySelector(".log-in");
		var logindiv = document.querySelector(".logindiv");

		const target = document.querySelector(".target");
		const links = document.querySelectorAll(".submenu");

		/*$('.submenu').hover(function(){
			mouseenterFunc();
		}, function(){
			mouseLeaveFunc();
		})
		 */
		for (let i = 0; i < links.length; i++) {
			links[i].addEventListener("mouseenter", mouseenterFunc);
			links[i].addEventListener("mouseleave", mouseLeaveFunc);
		}

		// 로그인 이벤트 끄기
		var exitlogo = document.querySelector(".exit-logo");
		loginbtn.addEventListener("click", function() {
			logindiv.style.right = "0px";
			for (let i = 0; i < opadiv.length; i++) {
				opadiv[i].style.opacity = "0.5";
			}
		});
		//------------------------
		function mouseenterFunc() {
			// 마우스가 올라가면 작동하는 함수

			if (!this.parentNode.classList.contains("active")) {
				for (let i = 0; i < links.length; i++) {
					// 액티브를 바꿈
					if (links[i].parentNode.classList.contains("active")) {
						links[i].parentNode.classList.remove("active");
					}
				}
				this.parentNode.classList.add("active");

				const width = this.getBoundingClientRect().width;
				const height = this.getBoundingClientRect().height;
				const left = this.getBoundingClientRect().left
						+ window.pageXOffset;
				const top = this.getBoundingClientRect().top
						+ window.pageYOffset;

				//alert(target+'/'+width+"/"+height+"/"+left+"/"+left);
				// 언더바가 따라다닐 위치 계산
				target.style.width = `${width}px`;
				target.style.height = `${height}px`;
				target.style.left = `${left}px`;
				target.style.top = `${top}px`;
				target.style.borderColor = "#8FAADC";
			}
		}

		function mouseLeaveFunc() {
			// 마우스가 떠나면 작동하는 함수
			for (let i = 0; i < links.length; i++) {
				links[i].parentNode.classList.remove("active");
			}
			target.style.borderColor = "transparent";
		}
	});
</script>



<body>
	<header>
		<div id="top2">
			<div class="app">
				<div class="header-div">
					<span class="search"> </span> <span class="title"> <a href="#">
							<h1 id="title">Trip Together</h1>
						</a>
					</span>
					<div class="container">
						<span id="menu"> <span class="ion-navicon-round"></span>
						</span>
					</div>

					<span class="log-in"> <c:if test="${logStatus !='Y'&& kakao==null}">
							<a href="#" class="log-in">
								<h4 id="login">LOGIN</h4>
							</a>
						</c:if> 
						<c:if test="${logStatus == 'Y'|| kakao!=null}">
							<c:if test="${kakao==null}">
								<a href="/mypage/myPlan">
									<img src="${logImg }" id="home_profile_img">
								</a>
							</c:if>

							<c:if test="${kakao !=null}">
								<a href="/mypage/myPlan" class="log-in">
									<img src="${kakao.properties.profile_image}" id="home_profile_img">
								</a>
							</c:if>
						</c:if>
					</span>
				</div>
				<nav class="option">
					<ul class="nav-child">
						<li><a href="${url}/main" class="submenu" id="submenu1">HOME</a></li>
						<li><a href="${url}/community/communityList" class="submenu" id="submenu2">COMMUNITY</a></li>
						<li><a href="${url}/plan/planSearch" class="submenu" id="submenu3">PLAN</a></li>
					</ul>
				</nav>
				<span class="target"></span>
			</div>
		</div>
	</header>

	<!-- <div style="background: black; z-index: -1; position: relative;"> -->
	<div class="opadiv" style="width: 100%; height: 1050px;">
		<video class="video" autoplay muted loop>
			<source src="${url}/videos/trip.mp4" type="video/mp4" />
		</video>
	</div>

	<div class="opadiv" style="width: 100%; height: 650px; background-color: white; display: flex;">
		<div class="intro_img_wrap">
			<a href="${url}/plan/planSearch">
				<img src="${url}/Images/intro_map.png" id="intro_img3">
			</a>
		</div>
		<div class="intro_phar_wrap">
			<div>
				나만의 여행계획을 세워보세요<br> Make your own travel plan<br>
				<button onclick="location.href='${url}/plan/planSearch'">+More</button>
			</div>
		</div>
	</div>

	<div class="opadiv" style="width: 100%; height: 650px; background-color: lightgray; display: flex;">
		<div class="intro_phar_wrap">
			<div>
				트투 친구들과 여행을 공유해보세요<br> Share your travel plan <br> with Trip Together friends<br>
				<button style="background-color: lightgray" onclick="location.href='${url}/community/communityList'">+More</button>
			</div>
		</div>
		<div class="intro_img_wrap">
			<a href="${url}/community/communityList">
				<img src="${url}/Images/intro_img2.png" id="intro_img2">
			</a>
		</div>
	</div>

	<div class="opadiv" style="width: 100%; height: 650px; background-color: white; display: flex;">
		<div class="intro_img_wrap">
			<img src="${url}/Images/intro_img4.png" id="intro_img3" style="width: 600px; margin-top: 110px; height: 400px;">
		</div>
		<div class="intro_phar_wrap">
			<div style="font-size: 1.4em; margin-top: 220px; line-height: 45px;">
				주변에서 특별함을 찾는데 도움을 드립니다<br> 소개하고 싶은 데이트 코스를 소개해보세요<br> 가고싶은 곳에 대한 궁금증을 해소해 드립니다<br> MBTI가 P이신가요?
				<a href="${url}/users/joinForm" style="color: gray;">
					<span style="font-weight: bold">회원가입</span>
				</a>
				하세요
			</div>
		</div>
	</div>

	<!-- </div>  -->

	<a id="MOVE_TOP_BTN" href="#">
		<img src="${url}/Images/back-to-top.png" style="width: 30px;">
	</a>


</body>

<script src="${url}/js/login.js"></script>
<script src="${url}/js/home/home.js"></script>
<script src="${url}/js/underBar.js"></script>
<script src="${url}/js/turnOver.js"></script>
</html>


