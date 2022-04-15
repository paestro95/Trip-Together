/**
 * 
 */
 
 $(function(){
	
	// 사용자 프로필/아이디 hover 시 색바꾸기 
	$(".user_img").hover(function(){
		$(this).css("opacity", "50%");
		$(this).next().css("opacity", "50%");
	},function(){
		$(this).css("opacity", "initial");
		$(this).next().css("opacity", "initial");
	});
	
	// 사용자 프로필/아이디 hover 시 색바꾸기 
	$(".id").hover(function(){
		$(this).css("opacity", "50%");
		$(this).prev().css("opacity", "50%");
	}, function(){
		$(this).css("opacity", "initial");
		$(this).prev().css("opacity", "initial");
	});
	
	// 팔로우 hover 시 색바꾸기
	$(".followBtn").hover(function(){
		$(this).css("opacity", "50%");
	}, function(){
		$(this).css("opacity", "initial");
	});
	
	// 이미지 hover시 확대
	$(".card_img").hover(function(){
		$(this).css("transform", "scale(1.1)").css("transition", "transform .5s");	
	}, function(){
		$(this).css("transform", "scale(1)").css("transition", "transform .5s");
	});

	// 좋아요 버튼 클릭 시 색바꾸기
	$(".like").click(function(){
		if($(this).html() == "favorite_border"){
			$(this).html("favorite");
		}else{
			$(this).html("favorite_border");
		}
	});
	
	// 저장 버튼 클릭 시 색바꾸기
	$(".save").click(function(){
		if($(this).html() == "bookmark_border"){
			$(this).html("bookmark");
		}else{
			$(this).html("bookmark_border");
		}
	});
	
	// 카테고리 중복선택 시 이전에 선택한 메뉴 삭제
	$(".category_list li").click(function(){
		var addv = "close" + $(this).text();
		$(".selected").each(function(index, item){
			var item = $(item).text();
			if(addv == item){
				$(this).remove();
			}
		});
		$(".select_cate_wrap").append("<div class='selected' value='"+$(this).html()+"'><span class='material-icons' style='cursor:pointer;vertical-align: middle;'>close</span>"+$(this).html()+"</div>");	
	});
	
	// x버튼 누르면 선택 메뉴 삭제
	$(document).on("click", ".selected span", function(){
		$(this).parent().remove();
	});
	
	// 선택한 카테고리 초기화
	$(document).on("click", ".select_reset", function(){
		$(".select_cate_wrap").empty();
	});
		
	
});
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
