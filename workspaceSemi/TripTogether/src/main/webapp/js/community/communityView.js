$(function(){
	var tagList = $("#hashtag_wrap").text();
	
	tagList = tagList.split(" ");
	var setTag = " ";
	
	for(var i=0; i<tagList.length; i++){
		setTag += "#" + tagList[i] + " ";
	}

	$("#hashtag_wrap").text(setTag);
});

function btn_notlogin(){
	alert("로그인 후 이용해 주세요.");
	$(".logindiv").css("right", "0px");
}

/* **************로그인 이벤트 출력************/
// 로그인 이벤트 출력
var loginbtn = document.querySelector(".log-in");
var logindiv = document.querySelector(".logindiv");


// 로그인 이벤트 끄기
var exitlogo = document.querySelector(".exit-logo");
loginbtn.addEventListener("click", function() {
	logindiv.style.right = "0px";
	for (let i = 0; i < opadiv.length; i++) {
		opadiv[i].style.opacity = "0.5";
	}
});

var opadiv = document.querySelectorAll(".opadiv");
exitlogo.addEventListener("click", function() {
	logindiv.style.right = "-500px";
	for (let i = 0; i < opadiv.length; i++) {
		opadiv[i].style.opacity = "1";
	}
});

$(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 500) {
			$('#MOVE_TOP_BTN').fadeIn();
		} else {
			$('#MOVE_TOP_BTN').fadeOut();
		}
	});

	$("#MOVE_TOP_BTN").click(function() {
		$('html, body').animate({
			scrollTop: 0
		}, 400);
		return false;
	});
});